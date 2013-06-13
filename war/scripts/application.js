$(document).ready(function(){
// Bindings
	//Nav Bar
	$("#login").bind({click: login});
	//$("#logout").bind({click: closeFbSession});}
	
	//My Feeds
	$("#myFeedsList .addTorrentBtn").bind({click: showFormAddTorrent});
	$("#myFeedsList .shareFeed").bind({click: shareFeed});
	$("#submitNewFeed").bind({click: newFeed});
	$("#newFeedBtn").bind({click: function (){
		$("#formNewFeed").slideToggle();
	}});
	
	
	//Modal
	$("#myFeedsModal article").bind({click: function(e){
			e.preventDefault();
			$("#myFeedsModal").find(".active").removeClass("active");
			$(this).addClass("active");
		}
	});
	$("#submitSharedTorrent").bind({click: addSharedTorrent});
	
	//Subscribed Feeds
	$("#subscribedFeedsList").find('.removeSubscribedFeed').bind({click: removeSubscribedFeed});
	
// One form for adding torrents for all the document.
	var formAddTorrent = $("<form id='formAddTorrent'><h3>Add Torrent</h3><hr><fieldset><label>Name</label><input id='torrentName' type='text' placeholder='Type torrent name...'></fieldset>"
				  + "<fieldset><label>Url</label><input id='torrentUrl' type='text' placeholder='Copy torrent url...'></fieldset>"
				  + "<button id='addTorrent' class='btn'><i class='icon-ok'></i></button><span id='cancelTorrent' class='btn'><i class='icon-remove'></i></span></form>").hide();

// Structure
	$(formNewFeed).hide();
	$("#addSharedTorrentModal").modal('hide');

// Action Functions
	function loadFeeds(){
		
		// Get Feeds and Torrents
		$.ajax({
		url : "/getFeeds" // The userId is persisted in the session
		type : "get",
		error : function(status) {
			alert("Error al cargar los feeds.");
		},
		success : function(data) {
			feeds = JSON.parse(data);
			if(feeds.size() > 0){
				
			}
		}
	}
	
	function newFeed(e){
		e.preventDefault();
		var feedName = $("#feedName").val();
		var feedDescription = $("#feedDescription").val();
		
		if(feedName == "" || feedDescription == ""){
			alert("Did you complete Feed Name and Description?");
			return;
		}
		
		$.ajax({
		url : "/newFeed?name=" + link
			+ "&description=" + torrentName,
		type : "post",
		error : function(status) {
			alert("Error al crear feed.");
		},
		success : function(data) {
			var response = JSON.parse(data);
			var feed = $("<article class='feed' data-key='"
			+ response.feedKey + "'><header><div class='btn-group actions'><button class='btn addTorrentBtn'><i class='icon-plus'></i></button><button class='btn shareFeed'><i class='icon-thumbs-up'></i></button></div>"
			+ "<h3><a href='" + response.feedHref + "'>"
			+ feedName + "</a></h3></header><aside><p>"
			+ feedDescription + "</p></aside><hr><ul class='torrents'></ul></article>");
			
			$("#myFeedsList #formNewFeed").after(feed).slideUp();
			$("#myFeedsList .addTorrentBtn").bind({click: showFormAddTorrent});
			$("#myFeedsList .shareFeed").bind({click: shareFeed});
			alert("Feed creado con éxito.");
		}
	}
	
	function showFormAddTorrent(e){
		e.preventDefault();
		//TODO: play with callback function of toggleSlide or animate.
		$("#myFeedsList #formAddTorrent").remove();
		$(formAddTorrent).insertBefore($(this).closest("article").find("ul")).slideDown();
		$("#addTorrent").bind({click: addTorrent});
		$("#cancelTorrent").bind({click: function(){
			$("#myFeedsList #formAddTorrent").slideUp()}});
	}
	
	function addTorrent(e){
		e.preventDefault();
		var torrentName = $('#torrentName').val();
		var torrentUrl = $('#torrentUrl').val();		
		var feedKey = $(this).closest("article").data("key");
		
		if(torrentName == "" || torrentUrl == ""){
			alert("Did you complete Torrent Name and Url?");
			return;
		}
		
		$.ajax({
		url : "/addTorrent?link=" + link
			+ "&name=" + torrentName
			+ "&feedKey=" + feedKey,
		type : "post",
		error : function(status) {
			alert("Error al agregar torrent.");
		},
		success : function() {
			var torrent = $("<li><a href='" + torrentUrl + "'>" + torrentName + "</a></li>");
			
			$('#myFeedsList #formAddTorrent').slideUp();
			$(this).closest('article').find('.torrents').prepend(torrent);
			postLink(torrentUrl);
		}
	}
	
	function addSharedTorrent(e){
		e.preventDefault();
		var torrentName = $("#addSharedTorrentModal").find(".sharedTorrent").text();
		var torrentUrl = $("#addSharedTorrentModal").find(".sharedTorrent").attr("href");
		
		var feedKey = $("#myFeedsModal").find(".active").data("key");
		
		alert('Name: ' + torrentName + ' Url:' + torrentUrl + ' Feed:' + feedKey);
		
		// Falta definir si se recarga el home con todos los feeds
		// ó si se agrega el nuevo torrent al feed ya cargado en el home.
	}
	
	function shareFeed(){
		var feedUrl = $(this).closest('article').find('a').attr('href');
		postLink(feedUrl);
	}
	
	function removeSubscribedFeed(){
		var feedKey = $(this).closest("article").find(".feed").data("key");
		
		alert('Feed: ' + feedKey);
		
		$(this).closest("article").slideUp(function(){
			$(this).remove();
		});
		
		// Llamada a servlet removeSubscribedFeed
	}
});

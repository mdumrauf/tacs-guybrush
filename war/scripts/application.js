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
	$("#addSharedTorrentModal").modal('show');

// Action Functions
	function newFeed(e){
		e.preventDefault();
		var feedName = $("#feedName").val();
		var feedDescription = $("#feedDescription").val();
		var feedHref = "#"; // Generar url del feed.
		var feedKey = "5435" // Generar key del feed.
		
		if(feedName == "" || feedDescription == ""){
			alert("Did you complete Feed Name and Description?");
			return;
		}
		
		var feed = $("<article><header><div class='btn-group actions'><button class='btn addTorrentBtn'><i class='icon-plus'></i></button><button class='btn shareFeed'><i class='icon-thumbs-up'></i></button></div>"
			+ "<h3><a class='feed' href='" + feedHref + "' data-key='" + feedKey + "'>"
			+ feedName + "</a></h3></header><aside><p>"
			+ feedDescription + "</p></aside><hr><ul class='torrents'></ul></article>");
			
		$("#myFeedsList #formNewFeed").after(feed).slideUp();
		$("#myFeedsList .addTorrentBtn").bind({click: showFormAddTorrent});
		$("#myFeedsList .shareFeed").bind({click: shareFeed});
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
		
		var feedKey = $(this).closest("article").find(".feed").data("key");
		
		if(torrentName == "" || torrentUrl == ""){
			alert("Did you complete Torrent Name and Url?");
			return;
		}
		
		var torrent = $("<li><a href='" + torrentUrl + "'>" + torrentName + "</a></li>");
		
		$('#myFeedsList #formAddTorrent').slideUp();
		$(this).closest('article').find('.torrents').prepend(torrent);
		
		// var link = $("#addTorrentTextBox").val();
		// $.ajax({
		// url : "/addTorrent?link=" + link,
		// type : "post",
		// error : function(status) {
			// alert("Error al agregar torrent");
		// },
		// success : function() {
			// alert("Torrent agregado con éxito");
		// }
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
		alert('Not implemented.');
	}
	
	function removeSubscribedFeed(){
		var feedKey = $(this).closest("article").find(".feed").data("key");
		
		alert('Feed: ' + feedKey);
		
		$(this).closest("article").slideUp(function(){
			$(this).remove();
		});
	}
});

$(document).ready(function(){
// Bindings
	$("#myFeedsList .addTorrentBtn").bind({click: showFormAddTorrent});
	$("#myFeedsList .shareFeed").bind({click: shareFeed});
	$("#submitNewFeed").bind({click: newFeed});
	$("#newFeedBtn").bind({click: function (){
		$("#formNewFeed").slideToggle();
	}});
	$("#login").bind({click: login});
	//$("#logout").bind({click: closeFbSession});
	
// One form for adding torrents for all the document.
	var formAddTorrent = $("<form id='formAddTorrent'><fieldset><label>Name</label><input id='torrentName' type='text' placeholder='Type torrent name...'></fieldset>"
				  + "<fieldset><label>Url</label><input id='torrentUrl' type='text' placeholder='Copy torrent url...'></fieldset>"
				  + "<button id='addTorrent' class='btn'><i class='icon-ok'></i></button><span id='cancelTorrent' class='btn'><i class='icon-remove'></i></span></form>").hide();

// Structure
	$(formNewFeed).hide();

// Action Functions
	function newFeed(e){
		e.preventDefault();
		var feedName = $("#feedName").val();
		var feedDescription = $("#feedDescription").val();
		var feedHref = "#"; // Generar url del feed.
		
		if(feedName == "" || feedDescription == ""){
			alert("Did you complete Feed Name and Description?");
			return;
		}
		
		var feed = $("<article><header><div class='actions'><button class='btn addTorrentBtn'><i class='icon-plus'></i></button><button class='btn shareFeed'><i class='icon-thumbs-up'></i></button></div><h3><a href='"
			+ feedHref + "'>"
			+ feedName + "</a></h3></header><aside><p>"
			+ feedDescription + "</p></aside><ul class='torrents'></ul></article>");
			
		$("#myFeedsList #formNewFeed").after(feed).slideUp();
		$("#myFeedsList .addTorrentBtn").bind({click: showFormAddTorrent});
		$("#myFeedsList .shareFeed").bind({click: shareFeed});
	}
	
	function showFormAddTorrent(e){
		e.preventDefault();
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
	
	function shareFeed(){
		alert('Not implemented.');
	}
});

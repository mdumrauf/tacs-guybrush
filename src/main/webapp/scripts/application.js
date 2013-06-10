$(document).ready(function(){
// Bindings
	$(".addTorrentBtn").bind({click: showTorrentForm});
	$(".shareFeed").bind({click: shareFeed});
	$("#submitNewFeed").bind({click: newFeed});
	$("#newFeedBtn").bind({click: function (){
		$("#formNewFeed").slideToggle();
	}});
	
// One form for adding torrents for all the document.
	var formAddTorrent = $("<form id='formAddTorrent'><fieldset><label>Name</label><input id='torrentName' type='text' placeholder='Type torrent name...'></fieldset>"
				  + "<fieldset><label>Url</label><input id='torrentUrl' type='text' placeholder='Copy torrent url...'></fieldset>"
				  + "<button id='addTorrent' class='btn'><i class='icon-ok'></i></button><span id='cancelTorrent' class='btn'><i class='icon-remove'></i></span></form>").hide();

// Structure
	$(formNewFeed).hide();
	$(formAddTorrent).find('#addTorrent').bind({click: addTorrent});
	$(formAddTorrent).find("#cancelTorrent").bind({click: function(){
		$("#myFeedsList #formAddTorrent").slideUp()}});

// Action Functions
	function newFeed(){
		var feedName = $("#feedName").val();
		var feedDescription = $("#feedDescription").val();
		var feedHref = "#"; // Generar url del feed.
		
		if(feedName == "" || feedDescription == ""){
			alert("Did you complete Feed Name and Description?");
			return;
		}
		
		var feed = $("<article><header><div class='actions'><button class='btn addTorrentBtn'><i class='icon-plus'></i></button>"
			+ "<button class='btn shareFeed'><i class='icon-thumbs-up'></i></button></div><h3><a href='"
			+ feedHref + "'>"
			+ feedName + "</a></h3></header><aside><p>"
			+ feedDescription + "</p></aside><ul class='torrents'></ul></article>");
			
		$("#formNewFeed").after(feed).slideUp();
		$(".addTorrentBtn").bind({click: showTorrentForm});
		$(".shareFeed").bind({click: shareFeed});
	}
	
	function showTorrentForm(e){
		e.preventDefault();
		$("#myFeedsList #formAddTorrent").hide().remove();
		$(formAddTorrent).prependTo($(this).closest("article").find("ul")).slideDown();
		$("#addTorrent").bind({click: addTorrent});
		$("#cancelTorrent").bind({click: function(){
		$("#formAddTorrent").slideUp()}});
	}
	
	function addTorrent(){
		var torrentName = $('#torrentName').val();
		var torrentUrl = $('#torrentUrl').val();
		
		if(torrentName == "" || torrentUrl == ""){
			alert("Did you complete Torrent Name and Url?");
			return;
		}
		
		var torrent = $("<li><a href='" + torrentUrl + "'>" + torrentName + "</a></li>");
		
		$('#formAddTorrent').slideUp();
		$(this).closest('ul').prepend(torrent);
	}
	
	function shareFeed(){
		alert('Not implemented.');
	}
});

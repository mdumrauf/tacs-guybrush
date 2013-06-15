$(document).ready(function(){
// Bindings
	//Nav Bar
	$('#login').on('click', login);
	$('#logout').on('click', closeFbSession);
	
	//My Feeds
	$('#myFeedsList').on('click', '.addTorrentBtn', showFormAddTorrent);
	$('#myFeedsList').on('click', '.shareFeed', shareFeed);
	
	//Form New Feed
	$('#newFeedBtn').on('click', function (e){
		e.preventDefault();
		$('#formNewFeed').slideToggle();}
	);
	$('#formNewFeed').find('.submitNewFeedBtn').on('click', newFeed);
	$('#formNewFeed').find('alert').hide();	
	
	//Form Add Torrent
	$('#formAddTorrent').find('.addTorrent').on('click', addTorrent);
	$('#formAddTorrent').find('.cancelTorrent').on('click', function(e){
		e.preventDefault();
		$('#formAddTorrent').slideUp();}
	);
	
	//Subscribed Feeds
	$('#subscribedFeedsList').on('click', '.removeSubscribedFeed', removeSubscribedFeed);
	
	//Modal Add Shared Torrent
	$("#myFeedsModal article").on('click', function(e){
			e.preventDefault();
			$("#myFeedsModal").find(".active").removeClass("active");
			$(this).addClass("active");
		}
	);
	$("#submitSharedTorrent").on('click', addSharedTorrent);
	
// Structure
	$('#addSharedTorrentModal').modal('hide');
	$('#formAddTorrent').hide();
	$('#formNewFeed').hide();

	$('#logo').on('click',loadMyFeeds);
	$('#logo').on('click',loadSubscribedFeeds);
	
// Load Feeds Dinamically
	function loadMyFeeds(){
		//Replace with $.getJSON('url/getFeeds');
		var json = [
			{'feed': {
				'key': '5432',
				'name': 'Horror Movies',
				'description': 'My favourite horror movies.',
				'owner': 'Alan Accurso',
				'torrents': [
						{'name': 'From dusk till dawn.', 'url': '#www.indiana.com/indy.torrent'},
						{'name': 'From dusk till dawn.', 'url': '#www.indiana.com/indy.torrent'}
					]
				}
			},
			{'feed': {
				'key': '5433',
				'name': 'Adventure Movies',
				'description': 'My favourite adventure movies.',
				'owner': 'Alan Accurso',
				'torrents': [
						{'name': 'Indiana Jones', 'url': '#www.indiana.com/indy.torrent'},
						{'name': 'Indiana Jones', 'url': '#www.indiana.com/indy.torrent'}
					]
				}
			}
		];
		
		var $myFeedsList = $('#myFeedsList');
		
		$.each(json, function(key, feeds){
			var feed = feeds.feed;
			
			var $newFeedTemplate = $myFeedsList.find('.feed.template');
			var $newFeed = $newFeedTemplate.clone().removeClass('template');
			
			$newFeed.data('feed-key', feed.key); //Generated by server
			$newFeed.find('.feedName').text(feed.name);
			$newFeed.find('.feedName').attr('href', '/getFeedRss?key=' + feed.key);
			$newFeed.find('.feedDescription').text(feed.description);
			
			var $torrents = $newFeed.find('.torrents');
			
			feed.torrents.forEach(function(torrent){
				var $newTorrent = $torrents.find('.template').clone().removeClass('template');
				$newTorrent.find('.torrent').text(torrent.name);
				$newTorrent.find('.torrent').attr('href',torrent.url);
				$newTorrent.prependTo($torrents);
				
			});
				
			$newFeed.appendTo($myFeedsList).hide().slideDown();
		});
	}
	
	function loadSubscribedFeeds(){
		//TODO: $.getJSON('url/getFeeds');
		
		var $subscribedFeedsList = $('#subscribedFeedsList');
		
		$.each(json, function(key, feeds){
			var feed = feeds.feed;
			
			var $newSubsFeedTemplate = $subscribedFeedsList.find('.feed.template');
			var $newSubsFeed = $newSubsFeedTemplate.clone().removeClass('template');
			
			$newSubsFeed.data('feed-key', feed.key);
			$newSubsFeed.find('.feedName').text(feed.name);
			$newSubsFeed.find('.feedName').attr('href', '/getFeedRss?key=' + feed.key);
			$newSubsFeed.find('.feedDescription').text(feed.description);
			$newSubsFeed.find('.owner').text(feed.owner);
			
			var $torrents = $newSubsFeed.find('.torrents');
			
			feed.torrents.forEach(function(torrent){
				var $newTorrent = $torrents.find('.template').clone().removeClass('template');
				$newTorrent.find('.torrent').text(torrent.name);
				$newTorrent.find('.torrent').attr('href',torrent.url);
				$newTorrent.prependTo($torrents);
				
			});
				
			$newSubsFeed.appendTo($subscribedFeedsList).hide().slideDown();
		});
	}

// Action Functions	
	function newFeed(e){
		e.preventDefault();
		var $formNewFeed = $('#formNewFeed');
		
		var feedName = $formNewFeed.find('.feedName').val();
		var feedDescription = $formNewFeed.find('.feedDescription').val();
		
		if(feedName == "" || feedDescription == ""){
			alert('The name or url of the feed are missing.');
			return;
		}
		
		var $newFeedTemplate = $('#myFeedsList').find('.feed.template');
		var $newFeed = $newFeedTemplate.clone().removeClass('template');
		
		$newFeed.data('feed-key','5432'); //Generated by server
		$newFeed.find('.feedName').text(feedName);
		$newFeed.find('.feedName').attr('href', '#/getFeed?feed-key=5432'); //getFeed?feed-key=5432
		$newFeed.find('.feedDescription').text(feedDescription);
		
		$newFeed.insertAfter($formNewFeed).slideDown();
		
		$formNewFeed.slideUp();
		
		// $.ajax({
		// url : "/newFeed?name=" + link
			// + "&description=" + torrentName,
		// type : "post",
		// error : function(status) {
			// alert("Error al crear feed.");
			// },
		// success : function(data) {
			// var response = JSON.parse(data);
			// var feed = $("<article class='feed' feed-key='"
			// + response.feedKey + "'><header><div class='btn-group actions'><button class='btn addTorrentBtn'><i class='icon-plus'></i></button><button class='btn shareFeed'><i class='icon-thumbs-up'></i></button></div>"
			// + "<h3><a href='" + response.feedHref + "'>"
			// + feedName + "</a></h3></header><aside><p>"
			// + feedDescription + "</p></aside><hr><ul class='torrents'></ul></article>");
			
			// $("#myFeedsList #formNewFeed").after(feed).slideUp();
			// alert("Feed creado con éxito.");
			// }
		// });
	}
	
	function showFormAddTorrent(e){
		e.preventDefault();
		var $formAddTorrent = $('#formAddTorrent');
		$formAddTorrent.hide();
		$formAddTorrent.appendTo($(this).closest("article").find("ul")).slideDown();
	}
	
	function addTorrent(e){
		e.preventDefault();
		var $formAddTorrent = $('#formAddTorrent');
		var torrentName = $formAddTorrent.find('.torrentName').val();
		var torrentUrl = $formAddTorrent.find('.torrentUrl').val();		
		var feedKey = $(this).closest("article").data("feed-key");
		
		if(torrentName == "" || torrentUrl == ""){
			$formAddTorrent.find('.alert').slideDown();
			return;
		}
		
		var $torrents = $(this).closest('.torrents');
		var $newTorrent = $torrents.find('.template').clone().removeClass('template');
		$newTorrent.find('.torrent').text(torrentName);
		$newTorrent.find('.torrent').attr('href',torrentUrl);
		$newTorrent.prependTo($torrents).hide().slideDown();
		
		$formAddTorrent.slideUp();
		
		// $.ajax({
		// url : "/addTorrent?link=" + link
			// + "&name=" + torrentName
			// + "&feedKey=" + feedKey,
		// type : "post",
		// error : function(status) {
			// alert("Error al agregar torrent.");
			// },
		// success : function() {
			// var torrent = $("<li><a href='" + torrentUrl + "'>" + torrentName + "</a></li>");
			
			// $('#myFeedsList #formAddTorrent').slideUp();
			// $(this).closest('article').find('.torrents').prepend(torrent);
			// postLink(torrentUrl);
			// }
		// });
	}
	
	function addSharedTorrent(e){
		e.preventDefault();
		var torrentName = $("#addSharedTorrentModal").find(".sharedTorrent").text();
		var torrentUrl = $("#addSharedTorrentModal").find(".sharedTorrent").attr("href");
		
		var feedKey = $("#myFeedsModal").find(".active").data("feed-key");
		
		alert('Name: ' + torrentName + '\nUrl:' + torrentUrl + '\nFeed:' + feedKey);
		
		// Falta definir si se recarga el home con todos los feeds
		// ó si se agrega el nuevo torrent al feed ya cargado en el home.
	}
	
	function shareFeed(){
		var feedUrl = $(this).closest('article').find('a').attr('href');
		postLink(feedUrl);
	}
	
	function removeSubscribedFeed(){
		var feedKey = $(this).closest("article").find(".feed").data("feed-key");
		
		alert('Feed: ' + feedKey);
		
		$(this).closest("article").slideUp(function(){
			$(this).remove();
		});
		
		// Llamada a servlet removeSubscribedFeed
	}
	
	//Mock data for Feeds
	var json = [
			{'feed': {
				'key': '5432',
				'name': 'Horror Movies',
				'description': 'My favourite horror movies.',
				'owner': 'Alan Accurso',
				'torrents': [
						{'name': 'From dusk till dawn.', 'url': '#www.indiana.com/indy.torrent'},
						{'name': 'From dusk till dawn.', 'url': '#www.indiana.com/indy.torrent'}
					]
				}
			},
			{'feed': {
				'key': '5433',
				'name': 'Adventure Movies',
				'description': 'My favourite adventure movies.',
				'owner': 'Alan Accurso',
				'torrents': [
						{'name': 'Indiana Jones', 'url': '#www.indiana.com/indy.torrent'},
						{'name': 'Indiana Jones', 'url': '#www.indiana.com/indy.torrent'}
					]
				}
			}
		];
});
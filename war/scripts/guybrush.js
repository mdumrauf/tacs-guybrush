$(document).ready(function() {

	guybrushApp.loadMyFeeds = loadMyFeeds;
	guybrushApp.loadSubscribedFeeds = loadSubscribedFeeds;
	guybrushApp.newFeed = newFeed;
	guybrushApp.showFormAddTorrent = showFormAddTorrent;
	guybrushApp.addTorrent = addTorrent;
	guybrushApp.addSharedTorrent = addSharedTorrent;
	guybrushApp.shareFeed = shareFeed;
	guybrushApp.removeSubscribedFeed = removeSubscribedFeed;


	function loadMyFeeds() {
		$.ajax({
			url : "/GetFeeds",
			type : "get",
			error : function(status) {
				alert("Error al intentar cargar los Feeds");
			},
			success : function(feedsJson) {
				var $myFeedsList = $('#myFeedsList');
				var feeds = $.parseJSON(feedsJson);
	
				feeds.forEach(function(feed) {
					var $newFeedTemplate = $myFeedsList.find('.feed.template');
					var $newFeed = $newFeedTemplate.clone().removeClass('template');
	
					$newFeed.data('feed-key', feed.key);
					$newFeed.find('.feedName').text(feed.title);
					$newFeed.find('.feedName').attr('href', '/GetFeed?feed=' + feed.key);
					$newFeed.find('.feedDescription').text(feed.description);
	
					var $torrents = $newFeed.find('.torrents');
	
					feed.items
						.forEach(function(item) {
							var $newTorrent = $torrents.find('.template').clone().removeClass('template');
							$newTorrent.find('.torrent').text(item.title);
							$newTorrent.find('.torrent').attr('href', item.link);
							$newTorrent.prependTo($torrents);
						});
	
					$newFeed.appendTo($myFeedsList).hide().slideDown();
				});
			}
		});
	}
	
	function loadSubscribedFeeds() {
		// TODO: $.getJSON('url/getSubsFeeds');
	
		var $subscribedFeedsList = $('#subscribedFeedsList');
	
		$.each(json, function(key, feeds) {
			var feed = feeds.feed;
	
			var $newSubsFeedTemplate = $subscribedFeedsList.find('.feed.template');
			var $newSubsFeed = $newSubsFeedTemplate.clone().removeClass('template');
	
			$newSubsFeed.data('feed-key', feed.key);
			$newSubsFeed.find('.feedName').text(feed.name);
			$newSubsFeed.find('.feedName').attr('href', '/GetFeed?feed=' + feed.key);
			$newSubsFeed.find('.feedDescription').text(feed.description);
			$newSubsFeed.find('.owner').text(feed.owner);
	
			var $torrents = $newSubsFeed.find('.torrents');
	
			feed.torrents
				.forEach(function(torrent) {
					var $newTorrent = $torrents.find('.template').clone().removeClass('template');
					$newTorrent.find('.torrent').text(torrent.name);
					$newTorrent.find('.torrent').attr('href', torrent.url);
					$newTorrent.prependTo($torrents);
				});
	
			$newSubsFeed.appendTo($subscribedFeedsList).hide().slideDown();
		});
	}
	
	// Action Functions
	function newFeed(e) {
		e.preventDefault();
		var $formNewFeed = $('#formNewFeed');
		var feed = {};
		feed.title = $formNewFeed.find('.feedName').val();
		feed.description = $formNewFeed.find('.feedDescription').val();
	
		if (feed.title == "" || feed.description == "") {
			alert('The name or description of the feed are missing.');
			return;
		}
	
		var $newFeed = $('#myFeedsList').find('.feed.template').clone().removeClass('template');
		
		$.ajax({
			url : "/NewFeed",
			type : "post",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(feed),
			error : function(status) {
				alert("Error al intentar guardar el Feed");
			},
			success : function(feedJson) {
				var $newFeedTemplate = $('#myFeedsList').find('.feed.template');
				var $newFeed = $newFeedTemplate.clone().removeClass('template');
				var feed = $.parseJSON(feedJson);
	
				$newFeed.data('feed-key', feed.key);
				$newFeed.find('.feedName').text(feed.title);
				$newFeed.find('.feedName').attr('href', '/GetFeed?feed=' + feed.key);
				$newFeed.find('.feedDescription').text(feed.description);
				
				$newFeed.insertAfter($formNewFeed).slideDown();
				$formNewFeed.slideUp();
			}
		});
	}
	
	function showFormAddTorrent(e) {
		e.preventDefault();
		var $formAddTorrent = $('#formAddTorrent');
		if($(this).closest('.feed').find('#formAddTorrent').length > 0){
			$formAddTorrent.slideToggle();
			return;
		}
		$formAddTorrent.hide();
		$formAddTorrent.appendTo($(this).closest(".feed").find(".torrents")).slideDown();
	}
	
	function addTorrent(e) {
		e.preventDefault();
		var $formAddTorrent = $('#formAddTorrent');
		var torrent = {};
		torrent.title = $formAddTorrent.find('.torrentName').val();
		torrent.link = $formAddTorrent.find('.torrentUrl').val();
		// TODO: description
		torrent.feed = $(this).closest(".feed").data("feed-key");
	
		if (torrent.title == "" || torrent.link == "") {
			alert('The name or url of the torrent are missing.');
			return;
		}
	
		$.ajax({
			url : "/AddTorrent?feed=" + torrent.feed,
			type : "post",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(torrent),
			error : function(status) {
				alert("Error al intentar agregar el torrent");
			},
			success : function(torrentJson) {
				var $torrents = $formAddTorrent.closest('.torrents');
				var $newTorrent = $torrents.find('.template').clone().removeClass('template');
				var torrent = $.parseJSON(torrentJson);
				$newTorrent.find('.torrent').text(torrent.title);
				$newTorrent.find('.torrent').attr('href', torrent.link);
				// TODO: description
				$newTorrent.prependTo($torrents).hide().slideDown();
	
				$formAddTorrent.slideUp();
	
				postTorrent(torrent.link, torrent.title);
			}
		});
	
	}
	
	// TODO: Not Used
	function addSharedTorrent(e) {
		e.preventDefault();
		$addSharedTorrentModal = $("#addSharedTorrentModal");
		var torrentName = $addSharedTorrentModal.find(".sharedTorrent").text();
		var torrentUrl = $addSharedTorrentModal.find(".sharedTorrent").attr("href");
	
		var feedKey = $addSharedTorrentModal.find(".myFeeds").find(".active").data("feed-key");
	
		alert('Name: ' + torrentName + '\nUrl:' + torrentUrl + '\nFeed:' + feedKey);
	
		// Falta definir si se recarga el home con todos los feeds
		// ó si se agrega el nuevo torrent al feed ya cargado en el home.
	}
	
	function shareFeed() {
		var $feed = $(this).closest('.feed');
		postFeed($feed.data('feed-key'), $feed.find('.feedName').text());
	}
	
	function removeSubscribedFeed() {
		var feedKey = $(this).closest(".feed").data("feed-key");
	
		// TODO: Llamada a servlet removeSubscribedFeed
		alert('Feed: ' + feedKey);
	
		$(this).closest(".feed").slideUp(function() {
			$(this).remove();
		});
	}
});
$(document).ready(function() {
// Bindings
	// Nav Bar
	$('.navbar').find('.about').on('click', function(e) {
		e.preventDefault();
		$('#aboutModal').modal();
	});

	// My Feeds
	$('#myFeedsList').on('click', '.addTorrentBtn', showFormAddTorrent);
	$('#myFeedsList').on('click', '.shareFeed', shareFeed);

	// Form New Feed
	$('#newFeedBtn').on('click', function(e) {
		e.preventDefault();
		$('#formNewFeed').slideToggle();
	});
	$('#formNewFeed').find('.submitNewFeedBtn').on('click', newFeed);

	// Form Add Torrent
	$('#formAddTorrent').find('.addTorrent').on('click', addTorrent);
	$('#formAddTorrent').find('.cancelTorrent').on('click', function(e) {
		e.preventDefault();
		$('#formAddTorrent').slideUp();
	});

	// Subscribed Feeds
	$('#subscribedFeedsList').on('click', '.removeSubscribedFeed', removeSubscribedFeed);

	// Modal Add Shared Torrent
	$("#myFeedsModal article").on('click', function(e) {
		e.preventDefault();
		$("#myFeedsModal").find(".active").removeClass("active");
		$(this).addClass("active");
	});
	$("#submitSharedTorrent").on('click', addSharedTorrent);

// Structure
	$('#addSharedTorrentModal').modal('hide');
	$('#formAddTorrent').hide();
	$('#formNewFeed').hide();
	// Refresh page.
	$('.navbar').find('.logo').on('click', loadMyFeeds);
	$('.navbar').find('.logo').on('click', loadSubscribedFeeds);

// Load Feeds Dinamically
	function loadMyFeeds() {
		// TODO: $.getJSON('url/getFeeds');

		var $myFeedsList = $('#myFeedsList');

		$.each(json, function(key, feeds) {
			var feed = feeds.feed;

			var $newFeedTemplate = $myFeedsList.find('.feed.template');
			var $newFeed = $newFeedTemplate.clone().removeClass('template');

			$newFeed.data('feed-key', feed.key); // Generated by server
			$newFeed.find('.feedName').text(feed.name);
			$newFeed.find('.feedName').attr('href', '/getFeedRss?feedKey=' + feed.key);
			$newFeed.find('.feedDescription').text(feed.description);

			var $torrents = $newFeed.find('.torrents');

			feed.torrents
				.forEach(function(torrent) {
					var $newTorrent = $torrents.find('.template').clone().removeClass('template');
					$newTorrent.find('.torrent').text(torrent.name);
					$newTorrent.find('.torrent').attr('href', torrent.url);
					$newTorrent.prependTo($torrents);
				});

			$newFeed.appendTo($myFeedsList).hide().slideDown();
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
			$newSubsFeed.find('.feedName').attr('href', '/getFeedRss?feedKey=' + feed.key);
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
				$newFeed.find('.feedName').attr('href', '/getFeedRss?feedKey=' + feed.key);
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
		var torrentName = $formAddTorrent.find('.torrentName').val();
		var torrentUrl = $formAddTorrent.find('.torrentUrl').val();
		var feedKey = $(this).closest(".feed").data("feed-key");

		if (torrentName == "" || torrentUrl == "") {
			alert('The name or url of the torrent are missing.');
			return;
		}

		// TODO: Llamada a servlet addTorrent

		var $torrents = $(this).closest('.torrents');
		var $newTorrent = $torrents.find('.template').clone().removeClass('template');
		$newTorrent.find('.torrent').text(torrentName);
		$newTorrent.find('.torrent').attr('href', torrentUrl);
		$newTorrent.prependTo($torrents).hide().slideDown();

		$formAddTorrent.slideUp();
		
		postTorrent(torrentUrl,torrentName);
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
		var $feed = $(this).closest('.feed').find('.feedName');
		postFeed($feed.data('feed-key'), $feed.text());
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

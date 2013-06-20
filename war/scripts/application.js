$(document).ready(function() {
// Bindings
	// Nav Bar
	$('nav').find('.about').on('click', function(e) {
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
	$('#formNewFeed').find('alert').hide();

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
	$('nav .logo').on('click', loadMyFeeds);
	$('nav .logo').on('click', loadSubscribedFeeds);

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
			$newFeed.find('.feedName').attr('href', '/getFeedRss?key=' + feed.key);
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
			$newSubsFeed.find('.feedName').attr('href', '/getFeedRss?key=' + feed.key);
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

		var feedName = $formNewFeed.find('.feedName').val();
		var feedDescription = $formNewFeed.find('.feedDescription').val();

		if (feedName == "" || feedDescription == "") {
			alert('The name or description of the feed are missing.');
			return;
		}

		// TODO: Llamada a servlet newFeed

		var $newFeedTemplate = $('#myFeedsList').find('.feed.template');
		var $newFeed = $newFeedTemplate.clone().removeClass('template');

		$newFeed.data('feed-key', '5432'); // Generated by server
		$newFeed.find('.feedName').text(feedName);
		$newFeed.find('.feedName').attr('href', '#/getFeed?feed-key=5432'); // getFeed?feed-key=5432
		$newFeed.find('.feedDescription').text(feedDescription);

		$newFeed.insertAfter($formNewFeed).slideDown();

		$formNewFeed.slideUp();
	}

	function showFormAddTorrent(e) {
		e.preventDefault();
		var $formAddTorrent = $('#formAddTorrent');
		if($(this).closest('.feed').find('#formAddTorrent').length > 0){
			$formAddTorrent.toggleSlide();
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
		
		// TODO: postTorrent(torrentUrl);
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
		var feedUrl = $(this).closest('.feed').find('.feedName').attr('href');
		// TODO: postFeed(feedUrl);
	}

	function removeSubscribedFeed() {
		var feedKey = $(this).closest(".feed").data("feed-key");

		// TODO: Llamada a servlet removeSubscribedFeed
		alert('Feed: ' + feedKey);

		$(this).closest(".feed").slideUp(function() {
			$(this).remove();
		});
	}

// Mock data for Feeds
	var json = [ {
		'feed' : {
			'key' : '5432',
			'name' : 'Horror Movies',
			'description' : 'My favourite horror movies.',
			'owner' : 'Alan Accurso',
			'torrents' : [ {
				'name' : 'From dusk till dawn.',
				'url' : '#www.indiana.com/indy.torrent'
			}, {
				'name' : 'From dusk till dawn.',
				'url' : '#www.indiana.com/indy.torrent'
			} ]
		}
	}, {
		'feed' : {
			'key' : '5433',
			'name' : 'Adventure Movies',
			'description' : 'My favourite adventure movies.',
			'owner' : 'Alan Accurso',
			'torrents' : [ {
				'name' : 'Indiana Jones',
				'url' : '#www.indiana.com/indy.torrent'
			}, {
				'name' : 'Indiana Jones',
				'url' : '#www.indiana.com/indy.torrent'
			} ]
		}
	} ];
});
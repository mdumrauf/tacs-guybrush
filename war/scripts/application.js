$(document).ready(function() {

	// Nav Bar
	$('.navbar').find('.about').on('click', function(e) {
		e.preventDefault();
		$('#aboutModal').modal();
	});
	$('.navbar').find('.help').on('click', function(e) {
		e.preventDefault();
		$('#helpModal').modal();
	});

	// My Feeds
	$('#myFeedsList').on('click', '.addTorrentBtn', guybrushApp.showFormAddTorrent);
	$('#myFeedsList').on('click', '.shareFeed', guybrushApp.shareFeed);

	// Form New Feed
	$('#newFeedBtn').on('click', function(e) {
		e.preventDefault();
		$('#formNewFeed').slideToggle();
	});
	$('#formNewFeed').find('.submitNewFeedBtn').on('click', guybrushApp.newFeed);

	// Form Add Torrent
	$('#formAddTorrent').find('.addTorrent').on('click', guybrushApp.addTorrent);
	$('#formAddTorrent').find('.cancelTorrent').on('click', function(e) {
		e.preventDefault();
		$('#formAddTorrent').slideUp();
	});

	// Subscribed Feeds
	$('#subscribedFeedsList').on('click', '.removeSubscribedFeed', guybrushApp.removeSubscribedFeed);

	// Structure
	$('#addSharedTorrentModal').modal('hide');
	$('#formAddTorrent').hide();
	$('#formNewFeed').hide();

	// Refresh page
	$('.navbar').find('.logo').on('click', guybrushApp.loadMyFeeds);

});

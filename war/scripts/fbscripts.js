$(document).ready(function() {

  guybrushApp.postTorrent = postTorrent;
  guybrushApp.postFeed = postFeed;

	var userId;
	var domain = 'localhost:8080';

	window.fbAsyncInit = function() {
		FB.init({
			appId : 175661185922694,
			channelUrl : '//' + domain + '/',
			status : true,
			cookie : true,
			xfbml : true
		});
	
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				loginOnServer(response.authResponse.userID);
			} else if (response.status === 'not_authorized') {
				FB.login(function(response) {
					loginOnServer(response.authResponse.userID);
				});
			} else {
				FB.login(function(response) {
					loginOnServer(response.authResponse.userID);
				});
			}
		});
	
	};

	// Load the SDK Asynchronously
	(function(d) {
		var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement('script');
		js.id = id;
		js.async = true;
		js.src = "//connect.facebook.net/en_US/all.js";
		ref.parentNode.insertBefore(js, ref);
	}(document));

	function postTorrent(torrentUrl, torrentName) {
		var obj = {
			method : 'feed',
			link : domain + '/AddTorrent?title=' + torrentName + '&link=' + torrentUrl + '&fromFB=true',
			picture : 'http://blog.doblajevideojuegos.es/wp-content/uploads/291135-gt3.jpg',
			name : torrentName,
			caption : 'Nuevo torrent agregado!',
			description : 'Haz click en el link para agregar el torrent a tus feeds.'
		};
	
		FB.ui(obj, postCallback);
	}
	
	function postFeed(feedKey, feedName) {
		var obj = {
			method : 'feed',
			link : domain + '/GetFeed?feed=' + feedKey,
			picture : 'http://blog.doblajevideojuegos.es/wp-content/uploads/291135-gt3.jpg',
			name : feedName,
			caption : 'Feed Rss Subscription!',
			description : 'Para suscribirte al feed copiá el link y pegalo en tu cliente de torrents favorito.'
		};
	
		FB.ui(obj, postCallback);
	}
	
	function postCallback(response) {
		if (response)
		{
			if (response.error)
			{  
				alert('El link no se posteó en Facebook');
			}
			else
			{
				alert('El link se posteó en Facebook exitosamente!');
			}
		}
		// user cancelled
		 
      
	}	
	
	function loginOnServer(uid) {
		userId = uid;
		$.ajax({
			url : "/login?userId=" + userId,
			type : "post",
			error : function(status) {
				alert("Error al loguear el usuario en el servidor");
			},
			success : function() {
				guybrushApp.loadMyFeeds();
			}
		});
	
		FB.api('/me', function(response) {
			var userName = $('.navbar #userName');
			userName.text(response.name);
			userName.attr("href", response.link);
		});
	}

});
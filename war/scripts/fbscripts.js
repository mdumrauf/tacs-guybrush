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

function postTorrent(link) {
	var obj = {
		method : 'feed',
		link : domain + 'addTorrent?link=' + link + '&fromFB=true',
		picture : 'img/guybrush.jpg',
		name : link,
		caption : 'Nuevo torrent!',
		description : 'Haz click en el link para agregar el torrent a tus feeds.'
	};

	FB.ui(obj, postCallback);
}

function postCallback(response) {
	if (response && response.post_id) {
		// TODO: Se posteo bien
	} else {
		alert('El link no se posteó correctamente');
	}
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

			// TODO: Load all user feeds (also subscribed feeds).

		}
	});

	FB.api('/me', function(response) {
		var userName = $('.navbar #userName');
		userName.text(response.name);
		userName.attr("href", response.link);
	});
}

function sendTorrentToServlet(link) {
	$.ajax({
		url : "/addTorrent?link=" + link + '&fromFB=false',
		type : "get",
		error : function(status) {
			alert("Error al agregar torrent");
		},
		success : function() {
			var response = confirm("Torrent agregado con éxito\n\n¿Querés compartirlo en tu muro?");
			if (response == true) {
				postTorrent(link);
			}
		}
	});
}

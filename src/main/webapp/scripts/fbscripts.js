var userId;
var domain = 'localhost:8080';
window.fbAsyncInit = function() {
	FB.init({
		appId : 175661185922694, // App ID
		channelUrl : '//' + domain + '/', // Channel File
		status : true, // check login status
		cookie : true, // enable cookies to allow the server to access the
						// session
		xfbml : true
	// parse XFBML - para escanear social plugins
	});
	
	FB.getLoginStatus(function(response) {
		if (response.status === 'connected') {
			// usuario logueado y autenticado en FB
			loginOnServer(response.authResponse.userID);
		} else {
			// usuario logueado en FB pero no autenticado (response.status === 'not_authorized')
			// o usuario no logueado en FB (response.status === 'unknown')
			loginOnFb();
		}
	});
	
	function loginOnFb() {
		FB.login(function(response){
				if(response.status === 'connected') {
					loginOnServer(response.authResponse.userID);
				}					
				else {
					alert('La aplicación requiere que se acepten los permisos');
					location.reload();
				}					
			});
	}
};

//Load the SDK Asynchronously
(function(d) {
	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement('script');
	js.id = id;
	js.async = true;
	js.src = "//connect.facebook.net/es_LA/all.js";
	ref.parentNode.insertBefore(js, ref);
}(document));

function loginOnServer(uid) {
	userId = uid;
	$.ajax({
		url : "/login?userId=" + userId,
		type : "post",
		error : function(status) {
			alert("Error al loguear el usuario en el servidor");
		},
		success : function() {
			var feedLink = domain + '/getFeed?userId=' + userId;
			$("#feedUrl").attr('href', feedLink);
			$("#appCommands").show();
		}
	});
}

function post() {
	FB.ui({
		method : 'feed'
	});
}

function addTorrent() {
	var link = $("#addTorrentTextBox").val();
	$.ajax({
		url : "/addTorrent?link=" + link,
		type : "post",
		error : function(status) {
			alert("Error al agregar torrent");
		},
		success : function() {
			alert("Torrent agregado con éxito");
		}
	});

}

function closeFbSession() {
	FB.logout(function(response) {
		$.ajax({
			url : "/logout?userId=" + response.authResponse.userID,
			type : "post",
			error : function(status) {
				alert("Error al desloguear el usuario en el servidor");
			},
			success : function() {
				$("#appCommands").hide();
			}
		});
	});

}

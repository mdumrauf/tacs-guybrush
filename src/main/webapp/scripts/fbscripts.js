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

	FB.Event.subscribe('auth.login', function(response) {
		loginOnServer(response.authResponse.userID);
	});
	
	FB.getLoginStatus(function(response) {
			  if (response.status === 'connected') {
				  loginOnServer(response.authResponse.userID);
			  }
		}
	);
	
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
	js.src = "//connect.facebook.net/es_LA/all.js";
	ref.parentNode.insertBefore(js, ref);
}(document));

function post(torrent) {
	 // calling the API ...
	if(torrent=='')torrent= 'www.google.com';
    var obj = {
      method: 'feed',
      link: torrent,
      picture: 'http://blog.popcap.com/wp-content/blogs.dir/3/2013/01/guybrush.jpg',
      name: torrent,
      caption: 'Nuevo torrent!',
      description: 'Has click en el link para agregar el torrent a tus feeds.'
    };

    function callback(response) {
      document.getElementById('msg').innerHTML = "Post ID: " + response['post_id'];
    }

    FB.ui(obj, callback);
}
function addTorrent() {
	var link = $("#addTorrentTextBox").val();
	$.ajax({
		url : "/addTorrent?link=" + link,
		type : "post",
		error : function(status) {
			alert("Error al agregar torrent");
		},
		success : function() {link
			alert("Torrent agregado con éxito");
			//var link = $("#addTorrentTextBox").val();
			post(link);
		}
	});
L
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

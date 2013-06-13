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

function postLink(torrent) {
    var obj = {
      method: 'feed',
      link: domain + 'addTorrent?link=' + torrent +'&fromFB=true' ,
      picture: 'http://blog.popcap.com/wp-content/blogs.dir/3/2013/01/guybrush.jpg',
      name: torrent,
      caption: 'Nuevo torrent!',
      description: 'Haz click en el link para agregar el torrent a tus feeds.'
    };

    FB.ui(obj, postCallback);
}

//@Deprecated
function post() {
    var obj = {
      method: 'feed'
    };
	
    FB.ui(obj, postCallback);
}

function postCallback(response) {
    	if (response && response.post_id) {
            //TODO: Se posteo bien
          } else {   
          	alert('El link no se posteó correctamente');
          	}
}

function login() {
		FB.login(function(response){
				loginOnServer(response.authResponse.userID);
			}
			//,{scope: 'email,user_likes'}
		);
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
			var feedLink = domain + '/getFeed?userId=' + userId;
			$("#feedUrl").attr('href', feedLink);
			$("#appCommands").show();
		}
	});
}

function addTorrent() {
	var link = $("#addTorrentTextBox").val();
	
	
	sendTorrentToServlet(link);
	
}
	
function sendTorrentToServlet(link){
	$.ajax({
		url : "/addTorrent?link=" + link+'&fromFB=false',
		type : "get",
		error : function(status) {
			alert("Error al agregar torrent");
		},
		success : function() {
			var response=confirm("Torrent agregado con éxito\n\n¿Querés compartirlo en tu muro?");
			if (response==true)
			{
				postLink(link);
			}
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

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
      link: domain + 'addTorrent?link=' +torrent ,
      picture: 'http://blog.popcap.com/wp-content/blogs.dir/3/2013/01/guybrush.jpg',
      name: torrent,
      caption: 'Nuevo torrent!',
      description: 'Has click en el link para agregar el torrent a tus feeds.'
    };

    function callback(response) {
    	if (response && response.post_id) {
            // Se posteo bien
          } else {   
          	alert('El link no se posteó correctamente');
          	}
          }    

    FB.ui(obj, callback);
}

function post() {
    var obj = {
      method: 'feed'
      
    };

    function callback(response) {
    	if (response && response.post_id) {
            // Se posteo bien
          } else {   
          	alert('No se pudo postear en su muro');
          	}
          }    

    FB.ui(obj, callback);
}

function addTorrent() {
	var link = $("#addTorrentTextBox").val();
	if(link==""){
		alert("Ingrese un link de un torrent");
		
	}
	else{
	
		sendTorrentToServlet(link);
	}
}
	
function sendTorrentToServlet(link){
	$.ajax({
		url : "/addTorrent?link=" + link,
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

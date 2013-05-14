var userId; 
window.fbAsyncInit = function() {
	    FB.init({
	      appId      : 475074325879764, // App ID
	      channelUrl : '//localhost:8080/', // Channel File
	      status     : true, // check login status
	      cookie     : true, // enable cookies to allow the server to access the session
	      xfbml      : true  // parse XFBML - para escanear social plugins
	    });
	    
	    FB.Event.subscribe('auth.login',
	    	    function(response) {
	    			loginOnServer(response.authResponse.userID);
	    	    }
	    	);
	
	    function loginOnServer(uid) {
	    	userId = uid;
	    	$.ajax({
	    			url: "/login?userId=" + userId,						
					type: "get",
					error: function(status){
						alert("Error al loguear el usuario en el servidor");
					},
					success: function(){
						var feedLink = 'localhost:8080/getFeed?userId=' + userId;
						$("#feedUrl").attr('href', feedLink);
						$("#appCommands").show();
					}
	    	});
	    }
	
	  };
	
	  // Load the SDK Asynchronously
	  (function(d){
	     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement('script'); js.id = id; js.async = true;
	     js.src = "//connect.facebook.net/es_LA/all.js";
	     ref.parentNode.insertBefore(js, ref);
	   }(document));
	  
	  
		function post(){
			  FB.ui({ 
		          method: 'feed' 
		        });
		}
		
		function addTorrent() {
			var link = $("#addTorrentTextBox").val();
	    	$.ajax({
    			url: "/addTorrent?link=" + link,						
				type: "get",
				error: function(status){
					alert("Error al agregar torrent");
				},
				success: function(){
					alert("Torrent agregado con éxito");
				}
    	});			
			
		}

		function closeFbSession(){
			FB.logout(function(response){
		    	$.ajax({
					url: "/logout?userId=" + response.authResponse.userID,						
					type: "get",
					error: function(status){
						alert("Error al desloguear el usuario en el servidor");
					},
					success: function(){
						$("#appCommands").hide();
					}
			});
		});
	
}

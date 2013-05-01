 window.fbAsyncInit = function() {
	    FB.init({
	      appId      : 475074325879764, // App ID
	      channelUrl : '//localhost:8080/', // Channel File
	      status     : true, // check login status
	      cookie     : true, // enable cookies to allow the server to access the session
	      xfbml      : true  // parse XFBML - para escanear social plugins
	    });
	    
	    
	    function testAPI() {
	        console.log('Welcome!  Fetching your information.... ');
	        FB.api('/me', function(response) {
	            console.log('Good to see you, ' + response.name + '.');
	        });
	    }
	    
	    function login() {
	        FB.login(function(response) {
	            if (response.authResponse) {
	                // connected
	            	testAPI();
	            	
	            } else {
	                // cancelled
	            }
	        });
	    }
	    	    
	    FB.getLoginStatus(function(response) {
	    	  if (response.status === 'connected') {
	    	    // connected
	    		  testAPI();
	    	  } else if (response.status === 'not_authorized') {
	    	    // not_authorized
	    		  // login();
	    	  } else {
	    	    // not_logged_in
	    		  // login();
	    	  }
	    	 });
	
	    // Additional init code here
	
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

function cerrarSesion(){
	FB.logout(function(response){})
}

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Guybrush</title>
	<link rel="stylesheet" href="/style/style.css" type="text/css" media="screen" />
	<script src="/scripts/jquery-1.10.0.min.js"></script>
</head>

<body>
  <div id="fb-root"></div>
  
  <div class="fb-login-button" show-faces=true></div>  
  <div class="fb-like" show-faces=false></div>
        
  <header>
  	<h1>Guybrush & friends</h1>
  </header>
    
  <nav>
	  <ul id="appCommands" style="visiblity:hidden">
		  <li><a id="post" href="#" >Postear</a></li>
		  <li>
		  	<input type="text" id="addTorrentTextBox" style="display:block"/>
		  	<button id="addTorrentButton">Compartir torrent</button>
		  </li>
		  <li>
		  	<a id="feedUrl">Link al feed</a>
		  </li>
		  <li id="logout"><a id="close-session" href="#">Cerrar sesión</a></li>
	  </ul>
  </nav>
  
  <script src="/scripts/fbscripts.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$('#post').bind({click: post});
  		$('#close-session').bind({click: closeFbSession});
		$("#appCommands").hide();
		$("#addTorrentButton").bind({click: addTorrent});
  	})
  </script>
		
</body>
</html>

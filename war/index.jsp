<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<html lang="en">
	<meta charset="UTF-8">
	<title>Guybrush</title>
	<link href="style/style.css" rel="stylesheet">
	<link href="style/bootstrap.min.css" rel="stylesheet">

	<script type="text/javascript">
	  var guybrushApp = {};
	</script>
</head>

<body>
	<div id="fb-root"></div>

	<!-- Navigation Bar -->
	<nav class='navbar navbar-fixed-top navbar-inverse'>
		<div class="navbar-inner">
			<a href="#" class='logo'><img src='img/logo/Original_266x75.png'></a>
			<ul class="nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a class="about" href='#about'>About</a></li>
				<li><a class="help" href='#help'>Help</a></li>
			</ul>
			<p class="navbar-text pull-right">
				Logged in as <a id="userName" href='#'>Username</a>
			</p>
		</div>
	</nav>

	<div class='wrapper'>
		<!-- My Feeds -->
		<section class="container feeds">
			<header>
				<div class='actions'>
					<button id='newFeedBtn' class='btn btn-primary'>New Feed <i class="icon-plus icon-white"></i></button>
				</div>
				<h2>My Feeds</h2>
			</header>
			<section id="myFeedsList">
				<!-- Feed Template -->
				<article class="feed template">
					<header>
						<div class="actions btn-group">
							<button type='button' class='btn addTorrentBtn'>
								<i class='icon-plus'></i>
							</button>
							<button type='button' class='btn shareFeed'>
								<i class='icon-thumbs-up'></i>
							</button>
						</div>
						<h3>
							<a class='feedName' href="#feedRss">Feed Name</a>
						</h3>
					</header>
					<aside>
						<p class='feedDescription'>Description of the feed.</p>
					</aside>
					<hr>
					<ul class="torrents">
						<li class="template"><a class='torrent' href='#torrentUrl'>Torrent Name</a></li>
					</ul>
				</article>
				<!-- New Feed Form -->
				<article id='formNewFeed'>
					<form>
						<h3>New Feed</h3>
						<hr>
						<fieldset>
							<label>Name</label> <input class="feedName" type="text"
								placeholder="Type feed name...">
						</fieldset>
						<fieldset>
							<label>Description</label>
							<textarea class="feedDescription" rows="2"></textarea>
						</fieldset>
						<button class="submitNewFeedBtn" type="submit" class="btn">Submit</button>
					</form>
				</article>
				<!-- List of Feeds -->
			</section><!-- End of Feeds List -->
		</section><!-- End of My Feeds -->
	</div><!-- End of Wrapper -->

	<!-- About Modal -->
	<div id="aboutModal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">About Guybrush</h3>
		</div>
		<div class="modal-body">
			<h4>Team</h4>
			<img class='aboutImg' src='img/guybrush.jpg'>
			<ul>
				<li>Accurso Alan</li>
				<li>Dumrauf Matías</li>
				<li>Galante Agustín</li>
				<li>Kosloff Guido</li>
				<li>Ponzo Natasha</li>
			</ul>
			<hr>
			<div class="fb-like"
					data-href="https://apps.facebook.com/tacs-guybrush"
					data-send="false" data-layout="button_count" data-width="450"
					data-show-faces="false" data-font="tahoma"></div>
		</div>
		<div class="modal-footer">
			<div class='logo'><img
				src='img/logo/Black_and_white_266x75.png'></div>
			<h4>TACS</h4>
		</div>
	</div>
	
	<!-- Help Modal -->
	<div id="helpModal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">Guybrush Help</h3>
		</div>
		<div class="modal-body">
			<h4>New Feed</h4>
			<p>To create a new feed click <button class='btn btn-primary btn-small'>New Feed <i class="icon-plus icon-white"></i></button></p>
			<p>Choose a name and description for the feed and click <button class="btn btn-small">Submit</button> to save it.</p>
			<hr>
			<h4>Add Torrent</h4>
			<p>To add a new torrent to an existing feed click <button type='button' class='btn btn-small'><i class='icon-plus'></i></button></p>
			<p>Insert the torrent url and choose an appropriate name.</p>
			<p>To save it click <button class='btn btn-small'><i class='icon-ok'></i></button></p>
			<p>To cancel the addition click <button class='btn btn-small'><i class='icon-remove'></i></button></p>
			<hr>
			<h4>Share Feed</h4>
			<p>To share a feed on Facebook click <button type='button' class='btn btn-small'><i class='icon-thumbs-up'></i></button></p>
			<hr>
			<h4>Guides to Use Rss Feed</h4>
			<ul>
			<li><a href='http://www.bittorrent.com/intl/es/help/guides/using-rss-feeds'>BitTorrent</a></li>
			<li><a href='http://www.utorrent.com/help/guides/rss'>uTorrent</a></li>
			</ul>
		</div>
		<div class="modal-footer">
			<div class='logo'><img
				src='img/logo/Black_and_white_266x75.png'></div>
			<h4>TACS</h4>
		</div>
	</div>

	<div style='display: none;'>
		<!-- Form Add Torrent -->
		<form id='formAddTorrent'>
			<h4>Add Torrent</h4>
			<hr>
			<fieldset>
				<label>Name</label> <input class='torrentName' type='text'
					placeholder='Type torrent name...'>
			</fieldset>
			<fieldset>
				<label>Url</label> <input class='torrentUrl' type='text'
					placeholder='Copy torrent url...'>
			</fieldset>
			<button class='btn addTorrent'>
				<i class='icon-ok'></i>
			</button>
			<button class='btn cancelTorrent'>
				<i class='icon-remove'></i>
			</button>
		</form>
	</div>
	
	<!-- Scripts -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script src="scripts/jquery-2.0.2.js"></script>
	<script src="scripts/guybrush.js"></script>
	<script src="scripts/application.js"></script>
	<script src="scripts/fbscripts.js"></script>
	<script src="scripts/bootstrap.min.js"></script>
</body>
</html>

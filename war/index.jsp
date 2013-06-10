<%@page pageEncoding="UTF-8"%>
<html lang="en">
<meta charset="UTF-8">
<title>Guybrush</title>
<link href="style/bootstrap.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
</head>

<body>

	<!-- Navigation Bar -->
	<nav class='navbar navbar-fixed-top navbar-inverse'>
		<div class="navbar-inner">
			<a class="brand" href="#">Guybrush</a>
			<ul class="nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Logout</a></li>
			</ul>
			<p class="navbar-text pull-right">
				<div class="fb-login-button"></div>
			</p>
		</div>
	</nav>

	<section class="row-fluid">
		<!-- My Feeds -->
		<section class="span6 feeds">
			<header>
				<div class='actions'>
					<button id='newFeedBtn' class='btn btn-primary'>New Feed</button>
				</div>
				<h2>My Feeds</h2>
			</header>
			<section id="myFeedsList">
				<article id='formNewFeed'>
					<form>
						<h3>New Feed</h3>
						<fieldset>
							<label>Name</label> <input id="feedName" type="text"
								placeholder="Type feed name...">
						</fieldset>
						<fieldset>
							<label>Description</label>
							<textarea id="feedDescription" rows="2"></textarea>
						</fieldset>
						<button id="submitNewFeed" type="submit" class="btn">Submit</button>
					</form>
				</article>
				<article>
					<header>
						<div class='actions'>
							<button class='btn addTorrentBtn'>
								<i class='icon-plus'></i>
							</button>
							<button class='btn shareFeed'>
								<i class='icon-thumbs-up'></i>
							</button>
						</div>
						<h3>
							<a href='#horrorFeed'>Horror Movies</a>
						</h3>
					</header>
					<aside>
						<p>Description</p>
					</aside>
					<ul class="torrents">
						<li><a href='#'>Torrent 1</a></li>
						<li><a href='#'>Torrent 2</a></li>
						<li><a href='#'>Torrent 3</a></li>
					</ul>
				</article>
				<article>
					<header>
						<div class='actions'>
							<button class='btn addTorrentBtn'>
								<i class='icon-plus'></i>
							</button>
							<button class='btn shareFeed'>
								<i class='icon-thumbs-up'></i>
							</button>
						</div>
						<h3>
							<a href='#horrorFeed'>Horror Movies</a>
						</h3>
					</header>
					<aside>
						<p>Description</p>
					</aside>
					<ul class="torrents">
						<li><a href='#'>Torrent 1</a></li>
						<li><a href='#'>Torrent 2</a></li>
						<li><a href='#'>Torrent 3</a></li>
					</ul>
				</article>
				<article>
					<header>
						<div class='actions'>
							<button class='btn addTorrentBtn'>
								<i class='icon-plus'></i>
							</button>
							<button class='btn shareFeed'>
								<i class='icon-thumbs-up'></i>
							</button>
						</div>
						<h3>
							<a href='#horrorFeed'>Horror Movies</a>
						</h3>
					</header>
					<aside>
						<p>Description</p>
					</aside>
					<ul class="torrents">
						<li><a href='#'>Torrent 1</a></li>
						<li><a href='#'>Torrent 2</a></li>
						<li><a href='#'>Torrent 3</a></li>
					</ul>
				</article>
			</section>
		</section>

		<!-- Subscribed Feeds -->
		<section class="span6 feeds">
			<header>
				<h2>Subscribed Feeds</h2>
			</header>
			<section id="subscribedFeedsList">
				<article>
					<header>
						<div class='actions'>
							<button class='btn'>
								<i class='icon-remove'></i>
							</button>
						</div>
						<h3>
							<a href='#horrorFeed'>Horror Movies</a>
						</h3>
					</header>
					<aside>
						<p>Description</p>
					</aside>
					<ul class="torrents">
						<li><a href='#'>Torrent 1</a></li>
						<li><a href='#'>Torrent 2</a></li>
						<li><a href='#'>Torrent 3</a></li>
					</ul>
				</article>
				<article>
					<header>
						<div class='actions'>
							<button class='btn'>
								<i class='icon-remove'></i>
							</button>
						</div>
						<h3>
							<a href='#horrorFeed'>Horror Movies</a>
						</h3>
					</header>
					<aside>
						<p>Description</p>
					</aside>
					<ul class="torrents">
						<li><a href='#'>Torrent 1</a></li>
						<li><a href='#'>Torrent 2</a></li>
						<li><a href='#'>Torrent 3</a></li>
					</ul>
				</article>
			</section>
		</section>
	</section>

	<footer>
		<p>&copy TACS</p>
	</footer>

	<!-- Scripts -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="scripts/fbscripts.js"></script>
	<script src="scripts/jquery-2.0.2.js"></script>
	<script src="scripts/application.js"></script>
	<script src="scripts/bootstrap.min.js"></script>
</body>
</html>

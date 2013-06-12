<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <html lang="en">
  <meta charset="UTF-8">
  <title>Guybrush</title>
  <link href="style/bootstrap.css" rel="stylesheet">
  <link href="style/style.css" rel="stylesheet">
</head>

<body>
  <div id="fb-root"></div>
  <!-- Navigation Bar -->
  <nav class='navbar navbar-fixed-top navbar-inverse'>
    <div class="navbar-inner">
      <a href="#"><img id='logo' src='img/logo/Original_266x75.png'></a>
      <ul class="nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#about">About</a></li>
        <li><a id="login" href="#login">Login</a></li>
        <li><a id="logout" href="#logout">Logout</a></li>
      </ul>
      <p class="navbar-text pull-right">
        Logged in as <a href='#'>Username</a>
      </p>
    </div>
  </nav>
  
  <!-- Add Shared Torrent Modal -->
  <div id="addSharedTorrentModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">Add Shared Torrent</h3>
      <a href="#sharedTorrentUrl" class="sharedTorrent">Shared Torrent</a>
    </div>
    <div class="modal-body">
      <h4>My Feeds</h4>
      <p>Please choose one of your feeds to add the shared torrent.</p>
      <hr>
      <div id='myFeedsModal'>
      <article><h4>Horror Movies</h4></article>
      <article><h4>Horror Movies</h4></article>
      <article><h4>Horror Movies</h4></article>
      <article><h4>Horror Movies</h4></article>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal">Close</button>
      <button class="btn btn-primary" type="submit" id="submitSharedTorrent">Save changes</button>
      <!-- <div class="alert alert-error">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <strong>Oh snap!</strong> This functionality is not implemented yet.
          </div> -->
    </div>
  </div>
  
  <!-- Feeds -->
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
            <hr>
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
            <div class="actions btn-group">
              <button type='button' class='btn addTorrentBtn'>
                <i class='icon-plus'></i>
              </button>
              <button type='button' class='btn shareFeed'>
                <i class='icon-thumbs-up'></i>
              </button>
            </div>
            <h3>
              <a href='#horrorFeed'>Horror Movies</a>
            </h3>
          </header>
          <aside>
            <p>Description of the feed.</p>
          </aside>
          <hr>
          <ul class="torrents">
            <li><a href='#'>Torrent 1</a></li>
            <li><a href='#'>Torrent 2</a></li>
            <li><a href='#'>Torrent 3</a></li>
          </ul>
        </article>
        <article>
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
              <a href='#horrorFeed'>Horror Movies</a>
            </h3>
          </header>
          <aside>
            <p>Description of the feed.</p>
          </aside>
          <ul class="torrents">
            <li><a href='#'>Torrent 1</a></li>
            <li><a href='#'>Torrent 2</a></li>
            <li><a href='#'>Torrent 3</a></li>
          </ul>
        </article>
        <article>
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
              <a href='#horrorFeed'>Horror Movies</a>
            </h3>
          </header>
          <aside>
            <p>Description of the feed.</p>
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

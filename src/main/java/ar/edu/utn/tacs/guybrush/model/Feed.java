package ar.edu.utn.tacs.guybrush.model;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import nu.xom.Document;
import nu.xom.Element;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Feed {

	private Logger logger = Logger.getLogger(Feed.class.getName());
	private List<String> links = new ArrayList<String>();
	private long userId;
	private DatastoreService datastore;

	public Feed(long userId) {
		this.userId = userId;
	}

	public void addLink(String link) {
		datastore = DatastoreServiceFactory.getDatastoreService();
		Entity feed = this.getEntity();
		links = this.getLinks(feed);
		links.add(link);
		feed.setProperty(LINKS, links);
		datastore.put(feed);
	}

	private List<String> getLinks(Entity e) {
		return (List<String>) e.getProperty(LINKS);
	}

	private Entity getEntity() {
		Query q = new Query(FEED).setFilter(new FilterPredicate(USER_ID,
				FilterOperator.EQUAL, userId));
		PreparedQuery pq = datastore.prepare(q);
		Entity feed = pq.asSingleEntity();
		if (feed == null)
			feed = this.createEntity();
		return feed;
	}

	private Entity createEntity() {
		Entity feed = new Entity(FEED);
		feed.setProperty(USER_ID, userId);
		feed.setProperty(LINKS, new ArrayList<String>());
		return feed;
	}

	public String build() {
		datastore = DatastoreServiceFactory.getDatastoreService();
		Entity feed = this.getEntity();
		links = this.getLinks(feed);
		Element root = new Element(RSS);
		root.appendChild(this.buildRSSChannel());
		Document doc = new Document(root);
		logger.info(doc.toXML());
		return doc.toXML();
	}

	private Element buildRSSChannel() {
		Element channel = new Element(CHANNEL);
		Element channelTitle = new Element(TITLE);
		channelTitle.appendChild(CHANNEL_TITLE);
		Element channelLink = new Element(LINK);
		channelLink.appendChild("localhost:8080");
		Element channelDescription = new Element(DESCRIPTION);
		channelDescription.appendChild(APP_CHANNEL_DESCRIPTION);

		channel.appendChild(channelTitle);
		channel.appendChild(channelLink);
		channel.appendChild(channelDescription);

		return this.buildRSSItems(channel);
	}

	private Element buildRSSItems(Element channel) {
		for (String linkString : links) {
			Element item = new Element(ITEM);
			Element itemTitle = new Element(TITLE);
			Element itemLink = new Element(LINK);
			Element itemDescription = new Element(DESCRIPTION);
			itemTitle.appendChild(TORRENT_TITLE);
			itemLink.appendChild(linkString);
			itemDescription.appendChild(DESCRIPTION);

			item.appendChild(itemTitle);
			item.appendChild(itemLink);
			item.appendChild(itemDescription);

			channel.appendChild(item);
		}
		return channel;
	}
}

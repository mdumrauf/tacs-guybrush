package ar.edu.utn.tacs.guybrush;

import java.util.ArrayList;
import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import nu.xom.*;

public class Feed {
	private List<String> links = new ArrayList<String>();
	private int userId;
	DatastoreService datastore;
	
	public Feed(int userId) {
		this.userId = userId;
	}
	
	public void addLink(String link) {
		datastore = DatastoreServiceFactory.getDatastoreService();
		Entity feed = this.getEntity();
		links = this.getLinks(feed);
		links.add(link);
		feed.setProperty("links", links);
		datastore.put(feed);
	}
	
	private List<String> getLinks(Entity e) {
		return (List<String>)e.getProperty("links");
	}
	
	private Entity getEntity() {
		Query q = new Query("feed").setFilter(new FilterPredicate("userId",
	            										FilterOperator.EQUAL,
	            											userId));
		PreparedQuery pq = datastore.prepare(q);
		Entity feed = pq.asSingleEntity();
		if(feed == null)
			feed = this.createEntity();
		return feed;
	}
	
	private Entity createEntity() {
		Entity feed = new Entity("feed");
		feed.setProperty("userId", userId);
		feed.setProperty("links", new ArrayList<String>());
		return feed;
	}

	public String build() {
		datastore = DatastoreServiceFactory.getDatastoreService();
		Entity feed = this.getEntity();
		links = this.getLinks(feed);
		Element root = new Element("rss");
		Element channel = new Element("channel");
		for(String linkString : links) {
			Element item = new Element("item");
			Element link = new Element("link");
			link.appendChild(linkString);
			item.appendChild(link);
			channel.appendChild(item);
		}
		root.appendChild(channel);
		Document doc = new Document(root);
		System.out.print(doc.toXML());
		return doc.toXML();
	}		
}

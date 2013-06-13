package ar.edu.utn.tacs.group5.service;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

import ar.edu.utn.tacs.group5.model.Feed;


public class FeedService {

	private static final String DEFAULT_FEED = "My Feed";

	public void insert(Long userId) {
		Feed feed = new Feed();
		feed.setUserId(userId);
		feed.setTitle(DEFAULT_FEED);
		insert(feed);
	}

	public void insert(Feed feed) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tx, feed);
		tx.commit();
	}

}

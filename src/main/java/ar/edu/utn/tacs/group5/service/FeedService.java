package ar.edu.utn.tacs.group5.service;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;

import ar.edu.utn.tacs.group5.meta.FeedMeta;
import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.model.Item;


public class FeedService {

	private static final String DEFAULT_FEED = "My Feed";
	private FeedMeta feedMeta = FeedMeta.get();

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

	public Feed getByUserId(Long userId) {
		return Datastore.query(feedMeta)
						.filter(feedMeta.userId.getName(), FilterOperator.EQUAL, userId)
						.asSingle();
	}

	public void addTorrent(Long userId, String link) {
		Feed feed = getByUserId(userId);
		Item item = new Item();
		item.setLink(link);
		item.getFeedRef().setModel(feed);
		Datastore.put(feed, item);
	}

}

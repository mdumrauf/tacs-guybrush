package ar.edu.utn.tacs.group5.controller;

import java.io.BufferedReader;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.meta.ItemMeta;
import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.model.Item;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.google.common.io.Closeables;
import com.google.common.net.MediaType;

public class AddTorrentController extends Controller {
	
	private static final String ALLOWED_CONTENT_TYPES = "Allowed Content-Types: application/json";
	private static final String ALLOWED_METHODS = "Allowed methods: POST";
	private static final String INVALID_FEED_KEY = "The provided feed key is invalid";

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private FeedService feedService = new FeedService();
	private ItemMeta itemMeta = ItemMeta.get();

    @Override
    public Navigation run() throws Exception {
    	Long userId = sessionScope(Constants.USER_ID);
    	if (userId == null) {
    		response.setStatus(HttpStatus.SC_FORBIDDEN);
			return null;
		}
    	if (!isPost()) {
			response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
			response.getWriter().print(ALLOWED_METHODS);
			return null;
		}
    	if (!MediaType.parse(request.getContentType()).is(MediaType.JSON_UTF_8)) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			response.getWriter().print(ALLOWED_CONTENT_TYPES);
			return null;
		}
    	String encodedFeedKey = param(Constants.FEED);
    	if (encodedFeedKey == null) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			return null;
		}
    	logger.info("Encoded Feed key: " + encodedFeedKey);
    	Feed feed;
		try {
			feed = feedService.getByKey(encodedFeedKey);
		} catch (IllegalArgumentException e) {
    		response.setStatus(HttpStatus.SC_BAD_REQUEST);
    		response.getWriter().print(INVALID_FEED_KEY);
			return null;
		}
    	logger.info(feed.toString());
    	BufferedReader reader = null;
		Item item;
		try {
			reader = request.getReader();
			String json = reader.readLine();
			item = itemMeta.jsonToModel(json);
		} finally {
			Closeables.closeQuietly(reader);
		}
		item.setFeed(feed);

		if (!item.isValid()) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			return null;
		}
		feedService.addItem(feed, item);

		if (Boolean.valueOf(param(Constants.FROM_FB))) {
			return redirect("index.jsp");
		}
		response.setStatus(HttpStatus.SC_CREATED);
		response.getWriter().print(itemMeta.modelToJson(item));
		return null;
    }

}

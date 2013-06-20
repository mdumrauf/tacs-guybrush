package ar.edu.utn.tacs.group5.controller;

import java.io.BufferedReader;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.meta.FeedMeta;
import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.google.common.io.Closeables;

public class NewFeedController extends Controller {

	private static final String ALLOWED_METHODS = "Allowed methods: POST";
	
	private FeedService feedService = new FeedService();
	private FeedMeta feedMeta = FeedMeta.get();
	
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
    	BufferedReader reader = null;
		Feed feed;
		try {
			reader = request.getReader();
			String json = reader.readLine();
			feed = feedMeta.jsonToModel(json);
		} finally {
			Closeables.closeQuietly(reader);
		}
		if (feed == null) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			return null;
		}
        feed.setUserId(userId);
        feedService.insert(feed);
        response.setStatus(HttpStatus.SC_CREATED);
        return null;
    }
}

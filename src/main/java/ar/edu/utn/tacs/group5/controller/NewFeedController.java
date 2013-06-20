package ar.edu.utn.tacs.group5.controller;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import ar.edu.utn.tacs.group5.meta.FeedMeta;
import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.service.FeedService;

public class NewFeedController extends Controller {

	private static final String ALLOWED_METHODS = "Allowed methods: POST";
	
	private FeedService feedService = new FeedService();
	
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
    	Feed feed = new Feed();
        BeanUtil.copy(request, feed);
        feed.setUserId(userId);
        feedService.insert(feed);
        return null;
    }
}

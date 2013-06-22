package ar.edu.utn.tacs.group5.controller;

import java.io.PrintWriter;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class GetFeedController extends Controller {

    private static final String ALLOWED_METHODS = "Allowed methods: GET";
	private static final String FEED_TEMPLATE = "feed.mustache";
	private MustacheFactory mustacheFactory = new DefaultMustacheFactory();
	private FeedService feedService = new FeedService();

	@Override
    public Navigation run() throws Exception {
    	Long userId = sessionScope(Constants.USER_ID);
    	if (userId == null) {
			response.setStatus(HttpStatus.SC_FORBIDDEN);
			return null;
		}
    	if (!isGet()) {
			response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
			response.getWriter().print(ALLOWED_METHODS);
			return null;
		}
        Mustache mustache = mustacheFactory.compile(FEED_TEMPLATE);
		Feed feed = feedService.getDefaultFeed(userId);
		mustache.execute(new PrintWriter(response.getWriter()), feed).flush();
    	return null;
    }

}

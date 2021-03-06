package ar.edu.utn.tacs.group5.controller;

import java.io.PrintWriter;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;

public class GetFeedController extends Controller {

    private static final String GET_FEED_URL_FORMAT = "%s/GetFeed?feed=%s";

    private static final String INVALID_FEED_KEY = "The provided feed key is invalid";
    private static final String ALLOWED_METHODS = "Allowed methods: GET";
    static final String FEED_TEMPLATE = "feed.mustache";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private MustacheFactory mustacheFactory = new DefaultMustacheFactory();
    private FeedService feedService = new FeedService();

    @Override
    public Navigation run() throws Exception {
        if (!isGet()) {
            response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
            response.getWriter().print(ALLOWED_METHODS);
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
        feed.setLink(buildFeedLink(feed.getKey()));
        response.setContentType(MediaType.XML_UTF_8.toString());
        response.setStatus(HttpStatus.SC_OK);
        Mustache mustache = mustacheFactory.compile(FEED_TEMPLATE);
        mustache.execute(new PrintWriter(response.getWriter()), feed).flush();
        return null;
    }

    static String buildFeedLink(Key key) {
        Preconditions.checkNotNull(key);
        return String.format(GET_FEED_URL_FORMAT, getHostUrl(), KeyFactory.keyToString(key));
    }

    static String getHostUrl() {
        final String hostUrl;
        String environment = System.getProperty(Constants.APP_ENGINE_ENV_PROPERTY);

        if (environment != null && Constants.PRODUCTION.contentEquals(environment)) {
            hostUrl = Constants.PROD_HOST;
        } else {
            hostUrl = Constants.DEV_HOST;
        }
        return hostUrl;
    }

}

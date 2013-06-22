package ar.edu.utn.tacs.group5.controller;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.parser.GAEGson;
import ar.edu.utn.tacs.group5.service.FeedService;

public class GetFeedsController extends Controller {

    private static final String ALLOWED_METHODS = "Allowed methods: GET";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private FeedService feedService = new FeedService();
    private GAEGson gaeGson = new GAEGson();

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
        List<Feed> all = feedService.getAll(userId);
        logger.info(all.toString());
        response.getWriter().print(gaeGson.toJsonString(all));
        return null;
    }

}

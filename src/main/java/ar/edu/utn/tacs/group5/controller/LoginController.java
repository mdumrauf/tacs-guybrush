package ar.edu.utn.tacs.group5.controller;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.service.FeedService;

import com.google.common.base.Preconditions;

public class LoginController extends Controller {

    private Logger logger = Logger.getLogger(LoginController.class.getSimpleName());
	private FeedService feedService = new FeedService();

	@Override
    public Navigation run() throws Exception {
    	String param = param(Constants.USER_ID);
    	Preconditions.checkNotNull(param);
    	Long userId = Long.valueOf(param);
		sessionScope(Constants.USER_ID, userId);
		logger.info("userId: " + param);

		if (feedService.hasDefaultFeed(userId)) {
			feedService.insert(userId);
		}
        return null;
    }
}

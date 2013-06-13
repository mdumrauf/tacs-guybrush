package ar.edu.utn.tacs.group5.controller;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.service.FeedService;

public class LoginController extends Controller {

    private Logger logger = Logger.getLogger(LoginController.class.getSimpleName());
	private FeedService feedService = new FeedService();

	@Override
    public Navigation run() throws Exception {
    	String userId = param(Constants.USER_ID);
		sessionScope(Constants.USER_ID, userId);
		logger.info("userId: " + userId);
		feedService.insert(Long.valueOf(userId));
        return null;
    }
}

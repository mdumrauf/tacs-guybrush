package ar.edu.utn.tacs.group5.controller;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LoginController extends Controller {

    private Logger logger = Logger.getLogger(LoginController.class.getSimpleName());

	@Override
    public Navigation run() throws Exception {
    	String userId = param(Constants.USER_ID);
		sessionScope(Constants.USER_ID, userId);
		logger.info("userId: " + userId);
        return null;
    }
}

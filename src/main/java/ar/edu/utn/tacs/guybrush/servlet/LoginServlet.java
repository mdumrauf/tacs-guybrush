package ar.edu.utn.tacs.guybrush.servlet;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.USER_ID;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1017068284122307416L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		long userId = ServletUtils.getUserId(req);
		HttpSession session = req.getSession();
		logger.info("User ID: " + userId);
		session.setAttribute(USER_ID, userId);
	}

}

package ar.edu.utn.tacs.guybrush.servlet;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.USER_ID;

import javax.servlet.http.HttpServletRequest;

final class ServletUtils {

	private ServletUtils() {
		// Utility class
	}

	static int getUserId(HttpServletRequest req) {
		return Integer.parseInt(req.getParameter(USER_ID));
	}
}

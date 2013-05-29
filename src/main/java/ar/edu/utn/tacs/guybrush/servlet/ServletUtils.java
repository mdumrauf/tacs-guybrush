package ar.edu.utn.tacs.guybrush.servlet;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.USER_ID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

final class ServletUtils {

	private ServletUtils() {
		// Utility class
	}

	static int getUserId(HttpServletRequest req) {
		return Integer.parseInt(req.getParameter(USER_ID));
	}

	static int getUserId(HttpSession session) {
		try {
			return (Integer) session.getAttribute(USER_ID);
		} catch (ClassCastException e) {
			throw new NumberFormatException(e.getMessage());
		}
	}
}

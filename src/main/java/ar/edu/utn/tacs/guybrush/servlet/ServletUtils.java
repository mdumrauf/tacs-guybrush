package ar.edu.utn.tacs.guybrush.servlet;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.USER_ID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

final class ServletUtils {

	private ServletUtils() {
		// Utility class
	}

	static long getUserId(HttpServletRequest req) {
		return Long.parseLong(req.getParameter(USER_ID));
	}

	static long getUserId(HttpSession session) {
		try {
			return (Long) session.getAttribute(USER_ID);
		} catch (ClassCastException e) {
			throw new NumberFormatException(e.getMessage());
		}
	}
}

package ar.edu.utn.tacs.guybrush.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.tacs.guybrush.model.Feed;

public class GetFeedServlet extends HttpServlet {

	private static final long serialVersionUID = 7120053373673461798L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		long userId = ServletUtils.getUserId(req.getSession());
		logger.info("User ID: " + userId);
		resp.getWriter().write(new Feed(userId).build());
	}

}

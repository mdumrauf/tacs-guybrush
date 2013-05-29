package ar.edu.utn.tacs.guybrush.servlet;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.LINK;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.tacs.guybrush.model.Feed;

public class AddTorrentServlet extends HttpServlet {

	private static final long serialVersionUID = 8656321060283652172L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String link = req.getParameter(LINK);
		long userId = ServletUtils.getUserId(req.getSession());
		new Feed(userId).addLink(link);
	}
}

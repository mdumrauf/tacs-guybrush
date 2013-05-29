package ar.edu.utn.tacs.guybrush;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.LINK;
import static ar.edu.utn.tacs.guybrush.model.FeedConstants.USER_ID;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.utn.tacs.guybrush.model.Feed;

public class AddTorrentServlet extends HttpServlet {

	private static final long serialVersionUID = 8656321060283652172L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String link = req.getParameter(LINK);
		int userId = (Integer) (session.getAttribute(USER_ID));
		new Feed(userId).addLink(link);
	}
}

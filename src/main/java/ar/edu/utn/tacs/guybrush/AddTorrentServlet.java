package ar.edu.utn.tacs.guybrush;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddTorrentServlet extends HttpServlet {

	private static final long serialVersionUID = 8656321060283652172L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String link = req.getParameter("link");
		int userId = (Integer) (session.getAttribute("userId"));
		new Feed(userId).addLink(link);
	}
}

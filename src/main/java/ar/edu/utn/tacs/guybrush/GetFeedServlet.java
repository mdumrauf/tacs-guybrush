package ar.edu.utn.tacs.guybrush;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFeedServlet extends HttpServlet {

	private static final long serialVersionUID = 7120053373673461798L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		resp.getWriter().write(new Feed(userId).build());
	}
}

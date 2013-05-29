package ar.edu.utn.tacs.guybrush;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1017068284122307416L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		HttpSession session = req.getSession();
		session.setAttribute("userId", userId);
	}

}

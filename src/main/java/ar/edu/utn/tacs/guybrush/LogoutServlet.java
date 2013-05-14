package ar.edu.utn.tacs.guybrush;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
			  	throws ServletException, IOException {
		  HttpSession session = req.getSession();
		  session.removeAttribute("userId");
	  }
}

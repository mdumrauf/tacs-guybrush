package ar.edu.utn.tacs.guybrush;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetFeedServlet extends HttpServlet {
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
			  	throws ServletException, IOException {
		  int userId = Integer.parseInt(req.getParameter("userId"));
		  resp.getWriter().write(new Feed(userId).build());
	  }
}

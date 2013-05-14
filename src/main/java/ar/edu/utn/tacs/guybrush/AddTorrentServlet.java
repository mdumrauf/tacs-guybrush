package ar.edu.utn.tacs.guybrush;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddTorrentServlet extends HttpServlet {
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
			  	throws ServletException, IOException {
		  HttpSession session = req.getSession();
		  String link = req.getParameter("link");
		  int userId = (Integer)(session.getAttribute("userId"));
		  new Feed(userId).addLink(link);	  
	  }
}

package ar.edu.utn.tacs.guybrush.servlet;

import static ar.edu.utn.tacs.guybrush.model.FeedConstants.LINK;
import static ar.edu.utn.tacs.guybrush.model.FeedConstants.FROM_FB;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.tacs.guybrush.model.Feed;

public class AddTorrentServlet extends HttpServlet {

	private static final long serialVersionUID = 8656321060283652172L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		String link = req.getParameter(LINK);
		String fromFB = req.getParameter(FROM_FB);
		long userId = ServletUtils.getUserId(req.getSession());
		logger.info(link);
		new Feed(userId).addLink(link);
		
		
		if(fromFB.equalsIgnoreCase("true")){
			resp.getWriter().write("Torrent agregado exitosamente!");
		}
	}
}

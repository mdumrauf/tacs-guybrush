package ar.edu.utn.tacs.group5.controller;

import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpStatus;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import ar.edu.utn.tacs.group5.service.FeedService;

public class AddTorrentController extends Controller {
	
	private static final String ALLOWED_METHODS = "Allowed methods: GET";
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private FeedService feedService = new FeedService();

    @Override
    public Navigation run() throws Exception {
    	Long userId = sessionScope(Constants.USER_ID);
    	if (userId == null) {
    		response.setStatus(HttpStatus.SC_FORBIDDEN);
			return null;
		}
    	if (!isGet()) {
			response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
			response.getWriter().print(ALLOWED_METHODS);
			return null;
		}
    	String link = param(Constants.LINK);
    	if (link == null) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			return null;
		}
    	logger.info(link);
		feedService.addTorrent(userId, link);

		if(Boolean.valueOf(param(Constants.FROM_FB))){
			return redirect("index.jsp");
		}
		return null;
    }
}

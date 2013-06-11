package ar.edu.utn.tacs.group5.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class AddTorrentController extends Controller {

    @Override
    public Navigation run() throws Exception {
    	String fromFB = param("from fb");
		if(fromFB.equalsIgnoreCase("true")){
			redirect("index.jsp");
		}
        return null;
    }
}

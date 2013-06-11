package ar.edu.utn.tacs.group5.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogoutController extends Controller {

    @Override
    public Navigation run() throws Exception {
    	removeSessionScope("userId");
        return redirect("/");
    }
}

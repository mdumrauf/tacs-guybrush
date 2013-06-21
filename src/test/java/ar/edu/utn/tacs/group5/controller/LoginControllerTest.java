package ar.edu.utn.tacs.group5.controller;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

public class LoginControllerTest extends AbstractControllerTest<LoginController> {

    @Test
    public void runOk() throws Exception {
        tester.param(Constants.USER_ID, "123456789");
        tester.start("/login");
        assertController(HttpStatus.SC_OK);
    }

    @Test
    public void runWithNullUserId() throws Exception {
    	tester.start("/login");
    	assertController(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void runWithInvalidUserId() throws Exception {
    	tester.param(Constants.USER_ID, "alphanumeric2342value999");
    	tester.start("/login");
    	assertController(HttpStatus.SC_BAD_REQUEST);
    }

}

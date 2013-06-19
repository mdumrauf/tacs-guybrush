package ar.edu.utn.tacs.group5.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LoginControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.param(Constants.USER_ID, "123456789");
        tester.start("/login");
        LoginController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }

    @Test(expected=NullPointerException.class)
    public void runWithNullUserId() throws Exception {
        tester.start("/login");
    }

    @Test(expected=IllegalArgumentException.class)
    public void runWithInvalidUserId() throws Exception {
    	tester.param(Constants.USER_ID, "alphanumeric2342value999");
        tester.start("/login");
    }
}

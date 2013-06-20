package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

public class LoginControllerTest extends AbstractControllerTest {

    @Test
    public void run() throws Exception {
        tester.param(Constants.USER_ID, "123456789");
        tester.start("/login");
        LoginController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }

    @Test
    public void runWithNullUserId() throws Exception {
        assertBadRequest();
    }

    @Test
    public void runWithInvalidUserId() throws Exception {
    	tester.param(Constants.USER_ID, "alphanumeric2342value999");
    	assertBadRequest();
    }

	private void assertBadRequest() throws IOException, ServletException {
		tester.start("/login");
        LoginController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.response.getStatus(), is(HttpStatus.SC_BAD_REQUEST));
	}
	
}

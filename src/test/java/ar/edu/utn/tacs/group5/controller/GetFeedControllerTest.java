package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

public class GetFeedControllerTest extends AbstractControllerTest {

	@Before
	public void before() throws IOException, ServletException {
		tester.param(Constants.USER_ID, "123456789");
	    tester.start("/Login");
	}
	
    @Test
    public void run() throws Exception {
        tester.param(Constants.LINK, "http://www.foo.com");
        tester.start("/GetFeed");
        GetFeedController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }

}

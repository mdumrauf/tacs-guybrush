package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NewFeedControllerTest extends AbstractAuthorizedControllerTest {

    @Test
    public void run() throws Exception {
        tester.start(resource());
        NewFeedController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }

	@Override
	protected String resource() {
		return "/NewFeed";
	}
}

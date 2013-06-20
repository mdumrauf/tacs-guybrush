package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

public abstract class AbstractAuthorizedControllerTest extends AbstractControllerTest {

	protected abstract String resource();

	@Test
	public void testRunIsForbidden() throws Exception {
		tester.start(resource());
		NewFeedController controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(HttpStatus.SC_FORBIDDEN));
	}

}
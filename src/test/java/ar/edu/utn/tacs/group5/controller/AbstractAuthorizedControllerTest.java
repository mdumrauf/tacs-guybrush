package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.slim3.controller.Controller;

public abstract class AbstractAuthorizedControllerTest<T extends Controller> extends AbstractControllerTest {

	protected abstract String resource();

	@Test
	public void testRunIsForbidden() throws Exception {
		tester.start(resource());
		assertController(HttpStatus.SC_FORBIDDEN);
	}

	protected void assertController(int expectedStatusCode) {
		T controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(expectedStatusCode));
	}

}
package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

import com.google.api.server.spi.config.ApiMethod.HttpMethod;

public class AddTorrentControllerTest extends AbstractAuthorizedControllerTest<AddTorrentController> {

	@Test
	public void runOk() throws Exception {
		doLogin();
		tester.param(Constants.LINK, "http://www.foo.com");
		tester.start(resource());
		assertController(HttpStatus.SC_OK);
	}

	@Test
	public void testRunIsNotAllowed() throws Exception {
		doLogin();
		tester.request.setMethod(HttpMethod.POST);
		tester.start(resource());
		assertController(HttpStatus.SC_METHOD_NOT_ALLOWED);
	}

	@Test
	public void testRunHasNoLink() throws Exception {
		doLogin();
		tester.request.setMethod(HttpMethod.GET);
		tester.start(resource());
		assertController(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void testRunFromFB() throws Exception {
		doLogin();
		tester.param(Constants.LINK, "http://www.foo.com");
		tester.param(Constants.FROM_FB, true);
		tester.request.setMethod(HttpMethod.GET);
		tester.start(resource());
		AddTorrentController controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(true));
		assertThat(tester.getDestinationPath(), is("index.jsp"));
		assertThat(tester.response.getStatus(), is(HttpStatus.SC_MOVED_TEMPORARILY));
	}

	@Override
	protected String resource() {
		return "/AddTorrent";
	}

}

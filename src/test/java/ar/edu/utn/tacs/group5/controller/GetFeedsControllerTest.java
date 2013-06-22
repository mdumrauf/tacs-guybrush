package ar.edu.utn.tacs.group5.controller;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

import com.google.api.server.spi.config.ApiMethod.HttpMethod;

public class GetFeedsControllerTest  extends AbstractAuthorizedControllerTest<GetFeedController> {

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

	@Override
	protected String resource() {
		return "/GetFeeds";
	}

}
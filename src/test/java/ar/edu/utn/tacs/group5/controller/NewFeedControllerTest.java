package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.common.net.MediaType;

public class NewFeedControllerTest extends AbstractAuthorizedControllerTest {

	@Test
	public void testRunIsCreated() throws Exception {
		doLogin();
		String feed = "{ name: \"foo\", description: \"bar\" }";
		BufferedReader reader = Mockito.mock(BufferedReader.class);
		when(reader.readLine()).thenReturn(feed);
		tester.request.setReader(reader);
		tester.request.setContentType(MediaType.JSON_UTF_8.toString());
		tester.request.setMethod(HttpMethod.POST);
		tester.start(resource());

		NewFeedController controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(HttpStatus.SC_CREATED));
	}

	@Test
	public void testRunIsNotAllowed() throws Exception {
		doLogin();
		tester.request.setMethod(HttpMethod.GET);
		tester.start(resource());

		NewFeedController controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(HttpStatus.SC_METHOD_NOT_ALLOWED));
	}
	
	@Test
	public void testRunContentTypeIsNotJson() throws Exception {
		doLogin();
		tester.request.setMethod(HttpMethod.POST);
		tester.request.setContentType(MediaType.FORM_DATA.toString());
		tester.start(resource());

		NewFeedController controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(HttpStatus.SC_BAD_REQUEST));
	}

	@Override
	protected String resource() {
		return "/NewFeed";
	}
}

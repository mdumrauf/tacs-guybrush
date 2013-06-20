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

public class NewFeedControllerTest extends AbstractAuthorizedControllerTest {

	@Test
	public void testRunIsCreated() throws Exception {
		doLogin();
		String feed = "{ name: \"foo\", description: \"bar\" }";
		BufferedReader reader = Mockito.mock(BufferedReader.class);
		when(reader.readLine()).thenReturn(feed);
		tester.request.setReader(reader);
		tester.request.setMethod(HttpMethod.POST);
		tester.start(resource());

		NewFeedController controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(HttpStatus.SC_CREATED));
	}

	@Override
	protected String resource() {
		return "/NewFeed";
	}
}

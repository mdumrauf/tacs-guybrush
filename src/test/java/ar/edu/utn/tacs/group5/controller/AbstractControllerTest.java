package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slim3.controller.Controller;
import org.slim3.tester.ControllerTestCase;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public abstract class AbstractControllerTest<T extends Controller> extends ControllerTestCase {

	protected LocalServiceTestHelper helper;

	@Override
	public void setUp() throws Exception {
		LocalDatastoreServiceTestConfig dsConfig = new LocalDatastoreServiceTestConfig();
		dsConfig.setNoStorage(true);
		dsConfig.setNoIndexAutoGen(true);
		helper = new LocalServiceTestHelper(dsConfig);
		helper.setUp();
		super.setUp();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		helper.tearDown();
	}

	protected void doLogin() throws IOException, ServletException {
		tester.param(Constants.USER_ID, "123456789");
		tester.start("/Login");
	}

	protected void assertController(int expectedStatusCode) {
		T controller = tester.getController();
		assertThat(controller, is(notNullValue()));
		assertThat(tester.isRedirect(), is(false));
		assertThat(tester.getDestinationPath(), is(nullValue()));
		assertThat(tester.response.getStatus(), is(expectedStatusCode));
	}

}
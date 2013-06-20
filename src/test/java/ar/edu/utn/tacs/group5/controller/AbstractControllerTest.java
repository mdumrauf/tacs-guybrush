package ar.edu.utn.tacs.group5.controller;



import org.slim3.tester.ControllerTestCase;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public abstract class AbstractControllerTest extends ControllerTestCase {

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

}
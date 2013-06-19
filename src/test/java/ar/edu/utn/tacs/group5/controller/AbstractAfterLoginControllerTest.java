package ar.edu.utn.tacs.group5.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.slim3.tester.ControllerTestCase;

public abstract class AbstractAfterLoginControllerTest extends ControllerTestCase {


	@Before
	public void before() throws IOException, ServletException {
		tester.param(Constants.USER_ID, "123456789");
	    tester.start("/login");
	}

}
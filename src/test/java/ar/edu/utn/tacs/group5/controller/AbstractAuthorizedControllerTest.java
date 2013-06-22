package ar.edu.utn.tacs.group5.controller;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.slim3.controller.Controller;

public abstract class AbstractAuthorizedControllerTest<T extends Controller> extends
        AbstractControllerTest<T> {

    protected abstract String resource();

    @Test
    public void testRunIsForbidden() throws Exception {
        tester.start(resource());
        assertController(HttpStatus.SC_FORBIDDEN);
    }

}
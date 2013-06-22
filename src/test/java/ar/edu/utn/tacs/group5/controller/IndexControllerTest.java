package ar.edu.utn.tacs.group5.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class IndexControllerTest extends AbstractControllerTest<IndexController> {

    @Test
    public void test() throws Exception {
        tester.start("/");
        assertThat(tester.response.getStatus(), is(equalTo(HttpServletResponse.SC_OK)));
    }

}

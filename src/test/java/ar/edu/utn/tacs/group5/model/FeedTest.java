package ar.edu.utn.tacs.group5.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

public class FeedTest extends AppEngineTestCase {

    private Feed model = new Feed();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

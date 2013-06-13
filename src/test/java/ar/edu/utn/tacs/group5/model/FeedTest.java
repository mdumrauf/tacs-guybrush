package ar.edu.utn.tacs.group5.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FeedTest extends AppEngineTestCase {

    private Feed model = new Feed();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

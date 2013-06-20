package ar.edu.utn.tacs.group5.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

public class ItemTest extends AppEngineTestCase {

    private Item model = new Item();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

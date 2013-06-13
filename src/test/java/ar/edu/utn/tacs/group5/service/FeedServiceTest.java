package ar.edu.utn.tacs.group5.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import ar.edu.utn.tacs.group5.model.Feed;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FeedServiceTest extends AppEngineTestCase {

    private FeedService service = new FeedService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void insert() throws Exception {
        Feed feed = new Feed();
        service.insert(feed);
        assertThat(feed.getKey(), is(notNullValue()));
    }

}

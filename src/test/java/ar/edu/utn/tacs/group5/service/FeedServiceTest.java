package ar.edu.utn.tacs.group5.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import ar.edu.utn.tacs.group5.model.Feed;

public class FeedServiceTest extends AppEngineTestCase {

    private FeedService service = new FeedService();

    @Test
    public void testInsert() throws Exception {
        Feed feed = new Feed();
        service.insert(feed);
        assertThat(feed.getKey(), is(notNullValue()));
    }

	@Test
	public void testHasDefaultFeed() throws Exception {
		long userId = 123456789L;
		assertFalse(service.hasDefaultFeed(userId));
		Feed feed = new Feed();
		feed.setUserId(userId);
        service.insert(feed);
        assertTrue(service.hasDefaultFeed(userId));
	}

	@Test
	public void testGetByKey() throws Exception {
		Feed feed = new Feed();
		feed.setTitle("foo");
		feed.setDescription("Foo description");
        service.insert(feed);
        Feed feedByKey = service.getByKey(feed.getKey());
        assertNotNull(feedByKey);
        assertThat(feedByKey.getTitle(), is("foo"));
        assertThat(feedByKey.getDescription(), is("Foo description"));
	}

	@Test(expected = NullPointerException.class)
	public void testGetByKeyWithNullKey() throws Exception {
		service.getByKey(null);
	}

}

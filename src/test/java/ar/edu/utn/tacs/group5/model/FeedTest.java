package ar.edu.utn.tacs.group5.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import ar.edu.utn.tacs.group5.meta.FeedMeta;

public class FeedTest extends AppEngineTestCase {

    @Test
    public void testIsValid() throws Exception {
        Feed feed = new Feed();
        assertFalse(feed.isValid());
        feed = getParsedFeed();
        assertTrue(feed.isValid());
    }

    private Feed getParsedFeed() {
        String modelJson = "{ title: \"foo\", description: \"bar\", link: \"http://www.foo.com\" }";
        return FeedMeta.get().jsonToModel(modelJson);
    }
}

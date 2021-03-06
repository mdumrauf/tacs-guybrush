package ar.edu.utn.tacs.group5.controller;

import static ar.edu.utn.tacs.group5.controller.GetFeedController.FEED_TEMPLATE;
import static ar.edu.utn.tacs.group5.controller.GetFeedController.buildFeedLink;
import static ar.edu.utn.tacs.group5.controller.GetFeedController.getHostUrl;
import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.appengine.api.datastore.Entities;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class GetFeedControllerTest extends AbstractControllerTest<GetFeedController> {

    private final FeedService feedService = new FeedService();
    private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();

    @Test
    public void runOk() throws Exception {
        Feed feed = new Feed();
        feed.setTitle("My Feed");
        feed.setDescription("My Feed description");
        feedService.insert(feed);
        String keyToString = KeyFactory.keyToString(feed.getKey());
        feed.setLink("http://localhost:8888/GetFeed?feed=" + keyToString);
        tester.request.addParameter(Constants.FEED, keyToString);
        tester.start(resource());
        assertController(HttpStatus.SC_OK);
        Mustache feedTemplate = mustacheFactory.compile(FEED_TEMPLATE);
        Writer writer = new StringWriter();
        assertEquals(feedTemplate.execute(writer, feed).toString(), tester.response.getOutputAsString());
    }

    @Test
    public void testRunIsNotAllowed() throws Exception {
        tester.request.setMethod(HttpMethod.POST);
        tester.start(resource());
        assertController(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    private String resource() {
        return "/GetFeed";
    }

    @Test
    public void testGetHostUrlDev() {
        assertEquals(Constants.DEV_HOST, getHostUrl());
    }

    @Test
    public void testGetHostUrlProd() {
        System.setProperty(Constants.APP_ENGINE_ENV_PROPERTY, Constants.PRODUCTION);
        assertEquals(Constants.PROD_HOST, getHostUrl());
        System.setProperty(Constants.APP_ENGINE_ENV_PROPERTY, Constants.DEVELOPMENT);
    }

    @Test(expected = NullPointerException.class)
    public void testBuildFeedLinkWithNullKey() {
        buildFeedLink(null);
    }

    @Test
    public void testBuildFeedLink() {
        Key key = KeyFactory.createKey(Entities.PROPERTY_METADATA_KIND, 123456789L);
        String keyString = KeyFactory.keyToString(key);
        assertEquals("http://localhost:8888/GetFeed?feed=" + keyString, buildFeedLink(key));
    }

}

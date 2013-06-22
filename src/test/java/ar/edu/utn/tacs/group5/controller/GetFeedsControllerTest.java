package ar.edu.utn.tacs.group5.controller;

import static ar.edu.utn.tacs.group5.model.Feed.newFeed;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

import ar.edu.utn.tacs.group5.meta.FeedMeta;
import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.google.api.server.spi.config.ApiMethod.HttpMethod;

public class GetFeedsControllerTest extends AbstractAuthorizedControllerTest<GetFeedsController> {

    private FeedService feedService = new FeedService();
    private FeedMeta feedMeta = FeedMeta.get();;

    @Test
    public void runOk() throws Exception {
        doLogin();
        List<Feed> feeds = newArrayList();
        Long userId = tester.sessionScope(Constants.USER_ID);

        Feed defaultFeed = feedService.getDefaultFeed(userId);
        Feed feed = newFeed(userId, "One More Feed", "Feed description");
        Feed feed2 = newFeed(userId, "One More Feed 2", "Feed description 2");

        feeds.add(defaultFeed);
        feeds.add(feed);
        feeds.add(feed2);
        feedService.insert(feed);
        feedService.insert(feed2);

        tester.start(resource());
        assertController(HttpStatus.SC_OK);
        assertEquals(feedMeta.modelsToJson(feeds), tester.response.getOutputAsString());
    }

    @Test
    public void runOkWithDefaultOnly() throws Exception {
        doLogin();
        Long userId = tester.sessionScope(Constants.USER_ID);
        Feed defaultFeed = feedService.getDefaultFeed(userId);
        List<Feed> feeds = newArrayList(defaultFeed);
        tester.start(resource());
        assertController(HttpStatus.SC_OK);
        assertEquals(feedMeta.modelsToJson(feeds), tester.response.getOutputAsString());
    }

    @Test
    public void testRunIsNotAllowed() throws Exception {
        doLogin();
        tester.request.setMethod(HttpMethod.POST);
        tester.start(resource());
        assertController(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected String resource() {
        return "/GetFeeds";
    }

}
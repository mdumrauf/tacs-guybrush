package ar.edu.utn.tacs.group5.controller;

import static ar.edu.utn.tacs.group5.model.Feed.newFeed;
import static ar.edu.utn.tacs.group5.model.Item.newItem;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.model.Item;
import ar.edu.utn.tacs.group5.parser.GAEGson;
import ar.edu.utn.tacs.group5.service.FeedService;

import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GetFeedsControllerTest extends AbstractAuthorizedControllerTest<GetFeedsController> {

    private FeedService feedService = new FeedService();
    private GAEGson gaeGson = new GAEGson();

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

        Item item1 = newItem("Item 1", "http://item1.com");
        feedService.addItem(defaultFeed, item1);
        feedService.addItem(defaultFeed, newItem("Item 2", "http://item2.com"));
        feedService.addItem(feed, newItem("Item 3", "http://item3.com"));
        feedService.addItem(feed, newItem("Item 4", "http://item4.com"));
        feedService.addItem(feed2, newItem("Item 5", "http://item5.com"));
        feedService.addItem(feed2, newItem("Item 6", "http://item6.com"));

        tester.start(resource());
        assertController(HttpStatus.SC_OK);
        String modelsToJson = gaeGson.toJsonString(feeds);
        assertEquals(modelsToJson, tester.response.getOutputAsString());

        JsonArray feedsJsonArray = gaeGson.toJsonArray(modelsToJson);
        JsonObject feedJson = feedsJsonArray.get(0).getAsJsonObject();
        JsonArray itemsJsonArray = feedJson.get("items").getAsJsonArray();
        assertNotNull(itemsJsonArray);
        assertThat(itemsJsonArray.size(), is(2));
        String itemJson = itemsJsonArray.get(0).toString();
        assertEquals(gaeGson.toJsonString(item1, Item.class), itemJson);
    }

    @Test
    public void runOkWithDefaultOnly() throws Exception {
        doLogin();
        Long userId = tester.sessionScope(Constants.USER_ID);
        Feed defaultFeed = feedService.getDefaultFeed(userId);
        List<Feed> feeds = newArrayList(defaultFeed);
        tester.start(resource());
        assertController(HttpStatus.SC_OK);
        assertEquals(gaeGson.toJsonString(feeds), tester.response.getOutputAsString());
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
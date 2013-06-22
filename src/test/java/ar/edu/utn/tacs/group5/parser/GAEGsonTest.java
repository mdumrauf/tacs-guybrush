package ar.edu.utn.tacs.group5.parser;


import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tacs.group5.model.Feed;
import ar.edu.utn.tacs.group5.model.Item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GAEGsonTest {

    private GAEGson gaeGson;

    @Before
    public void setUp() throws Exception {
        gaeGson = new GAEGson();
    }

    @Test
    public void testToJsonStringList() throws Exception {
        List<String> list = newArrayList("foo", "bar", "bazz");
        String jsonString = gaeGson.toJsonString(list);
        assertEquals("[\"foo\",\"bar\",\"bazz\"]", jsonString);
    }

    @Test
    public void testToJsonStringListWithFeed() throws Exception {
        List<Item> list = newArrayList(
                Item.newItem("foo1", "http://foo1.com"),
                Item.newItem("foo2", "http://foo2.com"),
                Item.newItem("foo3", "http://foo3.com"));
        String listJson = "[" +
                "{\"title\":\"foo1\",\"link\":\"http://foo1.com\"}," +
                "{\"title\":\"foo2\",\"link\":\"http://foo2.com\"}," +
                "{\"title\":\"foo3\",\"link\":\"http://foo3.com\"}" +
            "]";
        Feed feed = Feed.newFeed(123456789L, "My Feed", "Feed description");
        feed.getItems().addAll(list);
        String feedJson = String.format(
                "{\"userId\":%s,\"title\":\"%s\",\"description\":\"%s\",\"items\":%s}",
                feed.getUserId(),
                feed.getTitle(),
                feed.getDescription(),
                listJson);
        assertEquals(feedJson, gaeGson.toJsonString(feed, Feed.class));
    }

    @Test
    public void testToJsonStringListWithItems() throws Exception {
        List<Item> list = newArrayList(
                Item.newItem("foo1", "http://foo1.com"),
                Item.newItem("foo2", "http://foo2.com"),
                Item.newItem("foo3", "http://foo3.com"));
        String jsonString = gaeGson.toJsonString(list);
        String listJson = "[" +
                "{\"title\":\"foo1\",\"link\":\"http://foo1.com\"}," +
                "{\"title\":\"foo2\",\"link\":\"http://foo2.com\"}," +
                "{\"title\":\"foo3\",\"link\":\"http://foo3.com\"}" +
            "]";
        assertEquals(listJson, jsonString);
    }

    @Test
    public void testToJsonArray() throws Exception {
        String listJson = "[" +
                "{\"title\":\"foo1\",\"link\":\"http://foo1.com\"}," +
                "{\"title\":\"foo2\",\"link\":\"http://foo2.com\"}," +
                "{\"title\":\"foo3\",\"link\":\"http://foo3.com\"}" +
            "]";
        JsonArray jsonArray = gaeGson.toJsonArray(listJson);
        assertTrue(jsonArray.isJsonArray());
        assertThat(jsonArray.size(), is(3));
        JsonObject jsonElement = (JsonObject) jsonArray.get(0);
        assertEquals("foo1", jsonElement.get("title").getAsString());
        assertEquals("http://foo1.com", jsonElement.get("link").getAsString());
    }

}

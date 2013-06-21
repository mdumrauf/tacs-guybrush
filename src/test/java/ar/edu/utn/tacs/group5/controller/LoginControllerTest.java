package ar.edu.utn.tacs.group5.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;

import ar.edu.utn.tacs.group5.service.FeedService;

public class LoginControllerTest extends AbstractControllerTest<LoginController> {

    @Test
    public void runOk() throws Exception {
        FeedService feedService = new FeedService();
        String userIdString = "123456789";
        Long userId = Long.valueOf(userIdString);
        assertFalse(feedService.hasDefaultFeed(userId));
        tester.param(Constants.USER_ID, userIdString);
        tester.start("/login");
        assertController(HttpStatus.SC_OK);
        assertTrue(feedService.hasDefaultFeed(userId));
    }

    @Test
    public void runWithNullUserId() throws Exception {
        tester.start("/login");
        assertController(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void runWithInvalidUserId() throws Exception {
        tester.param(Constants.USER_ID, "alphanumeric2342value999");
        tester.start("/login");
        assertController(HttpStatus.SC_BAD_REQUEST);
    }

}

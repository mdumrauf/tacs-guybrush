package ar.edu.utn.tacs.guybrush.servlet;


import static ar.edu.utn.tacs.guybrush.model.FeedConstants.USER_ID;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class ServletUtilsTest {

	@Test
	public final void testGetUserIdFromRequest() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getParameter(USER_ID)).thenReturn("1234");
		assertThat(ServletUtils.getUserId(req), is(1234l));
	}

	@Test
	public final void testGetUserIdLong() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getParameter(USER_ID)).thenReturn("100003725763683");
		assertThat(ServletUtils.getUserId(req), is(100003725763683l));
	}

	@Test(expected = NumberFormatException.class)
	public void testGetUserIdWithUserIdParameterNotIntegerFromRequest() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getParameter(USER_ID)).thenReturn("foo123bar");
		ServletUtils.getUserId(req);
	}

	@Test
	public final void testGetUserIdFromSession() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(req.getSession()).thenReturn(session);
		when(req.getSession().getAttribute(USER_ID)).thenReturn(1234l);
		assertThat(ServletUtils.getUserId(req.getSession()), is(1234l));
	}

	@Test(expected = NumberFormatException.class)
	public void testGetUserIdWithUserIdParameterNotIntegerFromSession() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(req.getSession()).thenReturn(session);
		when(req.getSession().getAttribute(USER_ID)).thenReturn("foo123bar");
		ServletUtils.getUserId(req.getSession());
	}

	@Test
	public final void testGetUserIdLongFromSession() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute(USER_ID)).thenReturn(100003725763683l);
		assertThat(ServletUtils.getUserId(req.getSession()), is(100003725763683l));
	}

}

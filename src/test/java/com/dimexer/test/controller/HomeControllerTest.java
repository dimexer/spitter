package com.dimexer.test.controller;

import static com.dimexer.spitter.controller.HomeController.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.dimexer.spitter.controller.HomeController;
import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;
import com.dimexer.spitter.service.SpittleService;

public class HomeControllerTest {

	@Test
	public void shouldDisplayRecentSpittles() {
		List<Spittle> recentSpittles = asList(new Spittle[] {
				new Spittle(), new Spittle(), new Spittle() });
		SpittleService spittleService = mock(SpittleService.class);

		Spitter spitter = new Spitter(-1, "test", "tst", "test", "test", null);
		when(spittleService.getSpittleFromLastLogin(spitter, DEFAULT_SPITTERS_PER_PAGE)).thenReturn(recentSpittles);
		HomeController controller = new HomeController(spittleService);
		Map<String, Object> model = new HashMap<String, Object>();
		
		Principal pr =  new UsernamePasswordAuthenticationToken(spitter, null);
		String viewName = controller.showHomePage(model, pr);
		
		assertEquals("home", viewName);
		assertSame(recentSpittles, model.get("spittles"));
		verify(spittleService).getSpittleFromLastLogin(spitter, DEFAULT_SPITTERS_PER_PAGE);
	}
}

package com.frlgrd.rssstream.utils;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateUtilsTest extends TestCase {

	@Test
	public void DateUtils_sameDay() throws Exception {
		assertTrue(DateUtils.sameDay(DateTime.now(), DateTime.now()));
		assertFalse(DateUtils.sameDay(DateTime.now(), DateTime.now().plusDays(1)));
		assertFalse(DateUtils.sameDay(DateTime.now().minusDays(1), DateTime.now()));
	}

	@Test
	public void DateUtils_daysBetween() throws Exception {
		assertEquals(1, DateUtils.daysBetween(DateTime.now(), DateTime.now().plusDays(1)));
		assertEquals(1, DateUtils.daysBetween(DateTime.now(), DateTime.now().minusDays(1)));
		assertEquals(0, DateUtils.daysBetween(DateTime.now(), DateTime.now()));
	}
}
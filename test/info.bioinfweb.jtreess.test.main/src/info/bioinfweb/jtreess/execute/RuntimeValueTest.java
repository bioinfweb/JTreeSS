package info.bioinfweb.jtreess.execute;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;

import org.junit.Test;



public class RuntimeValueTest {
	@Test
	public void test_UNIT_VALUE_PATTERN_expUpperCase() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("-7E-18em");
		assertTrue(matcher.matches());
		assertEquals("-7E-18", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("em", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_expLowerCase() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("-7e2em");
		assertTrue(matcher.matches());
		assertEquals("-7e2", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("em", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_percent() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("23%");
		assertTrue(matcher.matches());
		assertEquals("23", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("%", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_percentSpace() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("23 %");
		assertTrue(matcher.matches());
		assertEquals("23", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("%", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_zero() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("0");
		assertTrue(matcher.matches());
		assertEquals("0", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertNull(matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_decimal() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("2.8 pt");
		assertTrue(matcher.matches());
		assertEquals("2.8", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("pt", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_integer() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("25px");
		assertTrue(matcher.matches());
		assertEquals("25", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("px", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_vendorUnit() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("7.3-some-unit");
		assertTrue(matcher.matches());
		assertEquals("7.3", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("-some-unit", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
	
	
	@Test
	public void test_UNIT_VALUE_PATTERN_vendorUnitSpace() {
		Matcher matcher = RuntimeValue.UNIT_VALUE_PATTERN.matcher("7.3 -some-unit");
		assertTrue(matcher.matches());
		assertEquals("7.3", matcher.group(RuntimeValue.NUMBER_CAPTURING_GROUP));
		assertEquals("-some-unit", matcher.group(RuntimeValue.UNIT_CAPTURING_GROUP));
	}
}

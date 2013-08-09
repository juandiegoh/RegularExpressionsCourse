package com.jherenu.regexp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class JavaPatternMatcherTests {

	@Test
	public void Find_MatchRegexString_ShouldReturnTrue() {
		Pattern matchingRegexPattern = Pattern.compile("^hel.*ld$");
		String pattern = "hello world";
		Matcher matchingRegexMatcher = matchingRegexPattern.matcher(pattern);
		
		assertTrue(matchingRegexMatcher.find());
	}
	
	@Test
	public void Find_NotMatchRegexString_ShouldReturnFalse() {
		Pattern notMatchingRegexPattern = Pattern.compile("NOT A MATCH");
		String pattern = "hello world";
		Matcher notMatchingRegexMatcher = notMatchingRegexPattern.matcher(pattern);
		
		assertFalse(notMatchingRegexMatcher.find());
	}
	
	@Test
	public void ReplaceAll_MatchRegexString_ShouldReturnModifiedString() {
		Pattern matchingRegexPattern = Pattern.compile("@[^.]+\\.");
		String input = "juan@gmail.com, pepito@hotmail.com, another_mail@yahoo.com";
		Matcher matchingRegexMatcher = matchingRegexPattern.matcher(input);
		
		String expectedResult = "juan@mercadolibre.com, pepito@mercadolibre.com, another_mail@mercadolibre.com";
		String result = matchingRegexMatcher.replaceAll("@mercadolibre.");

		assertEquals(expectedResult, result);
	}
	
	@Test
	public void ReplaceAll_NotMatchRegexString_ShouldReturnSameString() {
		Pattern notMatchingRegexPattern = Pattern.compile("NOT A MATCH");
		String input = "any string";
		Matcher matchingRegexMatcher = notMatchingRegexPattern.matcher(input);
		
		String expectedResult = "any string";
		String result = matchingRegexMatcher.replaceAll("NOT GOING TO BE IN THE RESULT");

		assertEquals(expectedResult, result);
	}
	
	@Test
	public void Group_MatchRegexString_ShouldReturnTheGroupedString() {
		Pattern matchingRegexPattern = Pattern.compile("^([^@]+)@.*$"); // matches user of email
		String input = "juan@gmail.com";
		Matcher matchingRegexMatcher = matchingRegexPattern.matcher(input);
		
		matchingRegexMatcher.matches();
		String result = matchingRegexMatcher.group(1);
		
		String expectedResult = "juan";

		assertEquals(expectedResult, result);
	}
}

package com.jherenu.regexp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JavaStringRegexpTests {

	@Test
	public void Matches_MatchLiteralString_ShouldReturnTrue() {
		String matchingRegexp = "hello world";
		String pattern = "hello world";
		
		assertTrue(pattern.matches(matchingRegexp));
	}
	
	@Test
	public void Matches_MatchRegexString_ShouldReturnTrue() {
		String matchingRegexp = "^hel.*ld$";
		String pattern = "hello world";
		
		assertTrue(pattern.matches(matchingRegexp));
	}
	
	@Test
	public void Matches_NotMatchRegexString_ShouldReturnFalse() {
		String notMatchingRegexp = "NOT A MATCH";
		String pattern = "hello world";
		
		assertFalse(pattern.matches(notMatchingRegexp));
	}
	
	@Test
	public void ReplaceAll_MatchRegexString_ShouldReturnModifiedString() {
		String matchingRegex = "@[^.]+\\."; 
		String myString = "juan@gmail.com, pepito@hotmail.com, another_mail@yahoo.com";
		
		String result = myString.replaceAll(matchingRegex, "@mercadolibre.");
		
		String expectedResult = "juan@mercadolibre.com, pepito@mercadolibre.com, another_mail@mercadolibre.com";
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void ReplaceAll_NotMatchRegexString_ShouldReturnSameString() {
		String notMatchingRegex = "NOT A MATCH"; 
		String myString = "any string";
		
		String result = myString.replaceAll(notMatchingRegex, "NOT GOING TO BE IN THE RESULT");
		
		String expectedResult = "any string";
		assertEquals(expectedResult, result);
	}
}

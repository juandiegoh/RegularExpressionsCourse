package com.jherenu.regexp

import java.util.regex.Matcher
import java.util.regex.Pattern

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

import org.junit.Test

class GroovyPatternMatcherTests {

	@Test
	void "CompareSyntacticSugar_If match regex string should return true"() {

		String input = "hello world"

		assertTrue input ==~ /^hel.*ld$/
	}
	
	@Test
	void "Matches_if match regex string should return true"() {
		def matchingRegexPattern = ~/^hel.*ld$/
		String pattern = "hello world"
		def matchingRegexMatcher = matchingRegexPattern.matcher(pattern)
		
		assert matchingRegexMatcher.matches()
	}

	@Test
	void "Matches_if not match regex string it should return false"() {
		def notMatchingRegexPattern = ~/NOT A MATCH/
		String pattern = "hello world"
		def notMatchingRegexMatcher = notMatchingRegexPattern.matcher(pattern)

		assertFalse notMatchingRegexMatcher.matches()
	}

	@Test
	void "ReplaceAll_if a match regex it should return a modified string"() {
		def matchingRegexMatcher = "juan@gmail.com, pepito@hotmail.com, another_mail@yahoo.com" =~ /@[^.]+\./

		def expectedResult = "juan@mercadolibre.com, pepito@mercadolibre.com, another_mail@mercadolibre.com"
		def result = matchingRegexMatcher.replaceAll("@mercadolibre.")

		assertEquals expectedResult, result
	}

	@Test
	void "ReplaceAll_if not a match regex it should return the same string"() {
		def matchingRegexMatcher = "any string" =~ /NOT A MATCH/

		def expectedResult = "any string"
		def result = matchingRegexMatcher.replaceAll("NOT GOING TO BE IN THE RESULT")

		assertEquals(expectedResult, result)
	}

	@Test
	public void "Group_if a match regex it should return the grouped string"() {
		def matchingRegexMatcher = "juan@gmail.com" =~ /^([^@]+)@.*$/
		
		def expectedResult = "juan"
		def result = matchingRegexMatcher[0][1]

		assertEquals expectedResult, result
	}
}

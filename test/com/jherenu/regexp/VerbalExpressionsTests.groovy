package com.jherenu.regexp

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

class VerbalExpressionsTests {

	@Test
	void "Test_If match regex should return true"() {

		String input = "hello world"
		def regex = new VerbalExpressions().startOfLine().add("hel").anything().add("ld").endOfLine() // /^hel.*ld$/

		assertTrue regex.test(input)
	}

	@Test
	void "Test_if not match regex string it should return false"() {
		def regex = new VerbalExpressions().add("NOT A MATCH")
		String input = "hello world"

		assertFalse regex.test(input)
	}
	
	@Test
	void "Test_if match regex it should return true"() {
		String input = "juan@mail.com"
		def regex = new VerbalExpressions().startOfLine().add("j").anything().add("@").somethingBut(".").add("\\.").anything().endOfLine() // ^j.*@[^.]+\.
		
//		assertEquals "^j(.*)@([^.]+)\\.", regex.toString()
//		assertTrue Pattern.compile("^j(.*)@([^.]+)\\..*").matcher("juan@mail.com").matches()
		assertTrue regex.test("juan@mail.com")
	}

	@Test
	void "Replace_if a match regex it should return a modified string"() {
		def regex = new VerbalExpressions().add("@").somethingBut(".").add("\\.")
		def input = "juan@gmail.com, pep@hotmail.com, another_mail@yahoo.com"

		def expectedResult = "juan@verbalexpressions.com, pep@verbalexpressions.com, another_mail@verbalexpressions.com"
		String result = regex.replace(input, "@verbalexpressions.");
		
		assertEquals expectedResult, result
	}

	@Test
	void "Replace_if not a match regex it should return the same string"() {
		def regex = new VerbalExpressions().add("NOT A MATCH")
		def input = "any string"
		
		def expectedResult = "any string"
		def result = regex.replace(input, "NOT GOING TO BE IN THE RESULT")

		assertEquals expectedResult, result
	}

	@Test
	public void "Group_if you ask if there are groups it should return false"() {
		def input = "Are there groups in VerbalExpressions?"
		def notMatchingRegexMatcher = new VerbalExpressions().add("There are no groups by now!")
		
		assertFalse notMatchingRegexMatcher.test(input)
	}
}

package org.zhadaev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ParenthesesTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testZeroArgs() {
        Parentheses.main();

        Assert.assertEquals("0", output.toString().trim());
    }

    @Test
    public void testEmptyString() {
        String testStr = "";

        Parentheses.main(testStr);

        Assert.assertEquals("0", output.toString().trim());
    }

    @Test
    public void testDifferentCombinations() {
        Map<String, String> testStringToExpectedResult = new HashMap<String, String>(){{
            put("(()", "2 - ()");
            put(")()())", "4 - ()()");
            put(")(()())", "6 - (()())");
            put(")(", "0");
            put("(((((", "0");
            put(")))))", "0");
            put(")()(((()()())()())((", "16 - ()((()()())()())");
        }};

        testStringToExpectedResult.forEach((testString, expectedResult) -> {
            Parentheses.main(testString);

            Assert.assertEquals(expectedResult, output.toString().trim());

            output.reset();
        });
    }
}

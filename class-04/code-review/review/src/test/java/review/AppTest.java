/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package review;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {


    @Test public void testLinter() throws Exception {
        String output = App.linter("src/test/resources/small.js");
        String expected = "line 2 was bonkers";
        assertEquals(expected, output);
        output = App.linter("src/test/resources/big.js");
        expected = "line 1 was bonkersline 2 was bonkersline 3 was bonkersline 4 was bonkersline 5 was bonkersline 6 was bonkersline 8 was bonkersline 9 was bonkers";
        assertEquals(expected, output);
    }
}
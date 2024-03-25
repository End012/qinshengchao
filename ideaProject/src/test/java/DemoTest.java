import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class DemoTest {
    private Demo demo;
    private static List<String> resultList = new ArrayList<>();

    @Before
    public void setUp() {
        demo = new Demo();
    }

    @Test
    public void testResultString() {
        String inputString = "aabcccbbadd";
        String result = demo.resultString(inputString);
        assertEquals("dd",result);
    }

    @Test
    public void testGetDuplicateCharCount() {
        String inputString = "aabcccbbadd";
        List<Integer> countList = new ArrayList<Integer>();
        countList =  demo.getDuplicateCharCount(inputString,countList);
        assertEquals(6, countList.size());
    }

    @Test
    public void testRemoveDuplicateChar() {
        String inputString = "aabcccbbadd";
        List<Integer> countList = new ArrayList<Integer>();
        countList.add(2);
        countList.add(1);
        countList.add(3);
        countList.add(2);
        boolean continueFlag = demo.removeDuplicateChar(inputString,countList);
        assertEquals(true, continueFlag);
    }


    @Test
    public void testBeforeChar() {
        assertEquals('b', demo.beforeChar('c'));
    }
}
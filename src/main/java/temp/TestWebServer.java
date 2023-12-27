package temp;

import  org.testng.annotations.Test;
import org.testng.annotations.Parameters;


public class TestWebServer {
     @Parameters({ "number-of-times" })
     @Test(enabled = true)
    public void accessPage(int numberOfTimes) {
        while (numberOfTimes-- > 0) {
//            numberOfTimes --;
            System.out.println("TestAnnotation.test " + numberOfTimes );
        }
    }
}


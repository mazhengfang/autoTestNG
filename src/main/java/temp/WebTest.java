package temp;
import org.testng.annotations.Test;

public class WebTest {
//    protected int m_numberOfTimes;
////    protected String m_numberOfTimes1 = "super1";
////    protected String m_numberOfTimes2 = "super2";
////
////    public WebTest(){
////        m_numberOfTimes2 = "Father: WebTest1";
////        System.out.println( m_numberOfTimes2 );
////    }
////    public void init(){
////        m_numberOfTimes2 = "Father: WebTest2";
////        System.out.println( m_numberOfTimes2 );
////    }
////
////    public WebTest(String para1, String para2){
////        m_numberOfTimes1 = para1;
////        m_numberOfTimes2 = para2;
////        System.out.println( m_numberOfTimes1 +" " + m_numberOfTimes2 );
////
////
////    }
//
//
//   @Test
//    public void testServer() {
//        for (int i = 0; i < m_numberOfTimes; i++) {
//            // access the web page
//            System.out.println("TestAnnotation.test: " + i);
//        }
//    }

    private int m_numberOfTimes;
    private int  m_numberOfTimes_T;

    public WebTest() {
       //
    }
    public WebTest(int numberOfTimes) {
        m_numberOfTimes = numberOfTimes;
        m_numberOfTimes_T = numberOfTimes;
    }

   @Test
    public void test1() {
        for (int i = 0; i < m_numberOfTimes; i++) {
            if(i == (m_numberOfTimes - 1))            System.out.println("TestAnnotation.test: " + i);
        }
    }
}





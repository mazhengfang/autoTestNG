package temp;

import org.testng.annotations.Test;

public class ChildWebTest extends WebTest{

    private String m_numberOfTimes3= "super-test1";;
    private String m_numberOfTimes4 = "super-test2";;

    public ChildWebTest() {
        super();
        // do nothing

    }

//    public ChildWebTest(String a, String b) {
//        super(a,b);
//    }
    @Test(groups = {"test1"})
    public void test1(){

        System.out.println("test group ");
//        System.out.println(m_numberOfTimes2);
//        System.out.println(super.m_numberOfTimes2);
//        ChildWebTest childWebTest =new ChildWebTest();
//        super.init();
//         new ChildWebTest(m_numberOfTimes3,m_numberOfTimes4);
//        childWebTest.test();

//        System.out.println(super.m_numberOfTimes1);
//        System.out.println(m_numberOfTimes4);

    }
}

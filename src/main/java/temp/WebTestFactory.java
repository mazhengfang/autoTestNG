package temp;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class WebTestFactory {

   @Factory(dataProvider = "dp")
       public Object[] createInstances(int n) {
           Object[] result = new Object[n];
           for (int i = 0; i < n; i++) {
               result[i] = new WebTest(i );
           }
           return result;
       }



    @DataProvider(name = "dp")
    static public Object[][] dp() {
        return new Object[][] {
                new Object[] { 3 }
        };
    }


//@Factory
//    public Object[] createInstances() {
//        Object[] result = new Object[10];
//        for (int i = 0; i < 10; i++) {
//            result[i] = new WebTest(i * 10);
//        }
//        return result;
//    }

}


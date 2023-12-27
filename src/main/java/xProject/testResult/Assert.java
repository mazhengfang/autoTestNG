package xProject.testResult;


import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;


public class Assert {
    public static boolean flag = true;
    public static List<Error> errors = new ArrayList<>();

    public static void verifyEquals(Object actual, Object expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(Object actual, Object expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }


    public static void verifyEquals(byte[] actual, byte[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(byte[] actual, byte[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
    public static void verifyEquals(short[] actual, short[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(short[] actual, short[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(int[] actual, int[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(int[] actual, int[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(boolean[] actual, boolean[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(boolean[] actual, boolean[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(char[] actual, char[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(char[] actual, char[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(float[] actual, float[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(float[] actual, float[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(double[] actual, double[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(double[] actual, double[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }


    public static void verifyEquals(long[] actual, long[] expected, String message){
        try{
            assertEquals(actual, expected,message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }

    public static void verifyEquals(long[] actual, long[] expected){
        try{
            assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
}

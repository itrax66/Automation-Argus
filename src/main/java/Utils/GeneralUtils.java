package Utils;

import java.util.Random;

public class GeneralUtils {

    public static int generateRandomID(){
        Random r = new Random();
        return r.nextInt(999999999)+1000;
    }
}

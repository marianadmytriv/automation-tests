package utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeoutUtils {

    public static void waitFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
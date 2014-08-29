/**
 * Created by jonah-hooper on 2014/08/29.
 */

public class Logger {
    public static void log(String string) {
        if (string != null)
            System.out.println("log ".concat(string));
    }

    public static void log(String[] strings) {
        if (strings == null) return;
        for(String string : strings) {
            if (string != null)
                System.out.println("log ".concat(string));
        }
    }
}

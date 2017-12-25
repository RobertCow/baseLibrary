package soft.robert.com.xlibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by M 4700 on 2017/6/14.
 */

public class PhoneUtils {


    public static boolean isMobileNO(String mobiles) {

//        Pattern p = Pattern.compile("^((\\d3)|(\\d{3}\\-))?13[0-9]\\d{8}|15[89]\\d{8}|17[0-9]\\d{8}");
        Pattern p = Pattern.compile("^1(3[0-9]|4[579]|5[0-35-9]|8[0-9]|7[0136-8])\\d{8}$");

        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

      public static boolean isEmail(String mobiles) {

        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

        Matcher m = p.matcher(mobiles);
        return m.matches();

    }


}

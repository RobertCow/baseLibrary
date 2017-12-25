package soft.robert.com.xlibrary.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 输入法工具
 * Created by M 4700 on 2017/8/4.
 */

public class InputMethodUtil {

    public static void hide(Context context,View view){
        InputMethodManager imm  = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}

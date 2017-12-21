package soft.robert.com.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created on 2017/12/21.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class ToastUtil {

    public  void toast(Context context,String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}

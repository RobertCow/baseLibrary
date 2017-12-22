package ag.yinglingedu.com.xlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;



import java.util.HashMap;
import java.util.Map;



/**
 * Created by CC on 2017/5/3.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{
    private int layoutId;
    public Map<String,String> map = new HashMap<>();

    /*浸入式状态栏*/
    public void initStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    public abstract void init();//初始化
    public abstract void setListener();//设置监听

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


}

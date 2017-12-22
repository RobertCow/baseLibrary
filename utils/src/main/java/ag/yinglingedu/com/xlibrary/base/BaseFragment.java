package ag.yinglingedu.com.xlibrary.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by CC on 2017/5/25.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private Activity activity;
    public Map<String,String> map= new HashMap<>();

    public abstract void init();//初始化
    public abstract void setListener();//设置监听
    public void gotoActivity(Class<?> cls){
        startActivity(new Intent(getActivity(),cls));
    }
}

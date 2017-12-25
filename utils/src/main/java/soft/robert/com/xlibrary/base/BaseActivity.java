package soft.robert.com.xlibrary.base;

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

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.robert.com.utils.R;
import soft.robert.com.xlibrary.utils.LogUtils;
import soft.robert.com.xlibrary.utils.SupportMultipleScreensUtil;
import soft.robert.com.xlibrary.utils.Utils;


/**
 * Created by CC on 2017/5/3.
 */

public abstract class BaseActivity extends Activity{
    private int layoutId;
    public Map<String,String> map = new HashMap<>();
    private Unbinder mUnbinder;
    private View rootView;

    /*浸入式状态栏*/
    public void initStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    public abstract boolean isScale();//是否缩放
    public abstract boolean isScreenNormal();//是否竖屏
    public abstract int initView(Bundle savedInstanceState);//初始化View
    public abstract void initData(Bundle savedInstanceState);//初始化数据
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isScreenNormal()) {//竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{//横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        try {
            int layoutResID = initView(savedInstanceState);
            if (layoutResID != 0) {//如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
                rootView = findViewById(android.R.id.content);
                if (null != rootView && isScale()) {
                    if(isScreenNormal()) {//竖屏
                        SupportMultipleScreensUtil.init(Utils.getContext(),false);
                    }else{//横屏
                        SupportMultipleScreensUtil.init(Utils.getContext(),true);
                    }
                    SupportMultipleScreensUtil.scale(rootView);
                }
                View back= rootView.findViewById(R.id.iv_back);
                if (back != null) {
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }
            }
        } catch (Exception e) {
            LogUtils.e("----------"+e.toString());
        }
        initData(savedInstanceState);
    }


}

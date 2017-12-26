package soft.robert.com.githubdemo;


import android.os.Bundle;

import soft.robert.com.xlibrary.base.BaseActivity;

/**
 * Created on 2017/12/22.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class MainActivity extends BaseActivity {


    @Override
    public boolean isScale() {
        return true;
    }

    @Override
    public boolean isImmersion() {
        return false;
    }

    @Override
    public boolean isScreenNormal() {
        return true;
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}

package soft.robert.com.githubdemo;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import soft.robert.com.xlibrary.base.BaseActivity;
import soft.robert.com.xlibrary.dialog.UniversalDialog;
import soft.robert.com.xlibrary.utils.ChangeUtil;

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
        UniversalDialog universalDialog = new UniversalDialog(this, R.layout.confirm_dialog, Gravity.CENTER, new int[]{R.id.tv_left, R.id.tv_right});
        universalDialog.show();
        universalDialog.setCanceledOnTouchOutside(false);
        ((TextView) universalDialog.findViewById(R.id.tv_message)).setText("退出登录");
        ChangeUtil.changeTextColor((TextView) universalDialog.findViewById(R.id.tv_right),R.color.colorAccent);
        universalDialog.setOnDialogItemClickListener(new UniversalDialog.OnDialogItemClickListener() {
            @Override
            public void OnDialogItemClick(UniversalDialog dialog, View view) {
                switch (view.getId()){
                    case R.id.tv_left:

                        break;
                    case R.id.tv_right:

                        break;
                }
            }
        });

    }
}

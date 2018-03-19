package soft.robert.com.githubdemo;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import soft.robert.com.xlibrary.base.BaseActivity;
import soft.robert.com.xlibrary.dialog.UniversalDialog;
import soft.robert.com.xlibrary.utils.ChangeUtil;
import soft.robert.com.xlibrary.utils.CountDownHelper;

/**
 * Created on 2017/12/22.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @Override
    public boolean noScale() {
        return false;
    }

    @Override
    public boolean isImmersion() {
        return false;
    }

    @Override
    public boolean isLandscape() {
        return false;
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @SuppressLint("NewApi")
    @Override
    public void initData(Bundle savedInstanceState) {
        showConfirmDialog();
        final CountDownHelper countDownHelper = new CountDownHelper(tvGetCode,getDrawable(R.drawable.gray_get_code), R.color.white,10000,1000);
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownHelper.start();
            }
        });
    }

    private void showConfirmDialog() {
        UniversalDialog universalDialog = new UniversalDialog(this, R.layout.confirm_dialog, Gravity.CENTER, new int[]{R.id.tv_left, R.id.tv_right});
        universalDialog.show();
        universalDialog.setCanceledOnTouchOutside(false);
        ((TextView) universalDialog.findViewById(R.id.tv_message)).setText("退出登录");
        ChangeUtil.changeTextColor((TextView) universalDialog.findViewById(R.id.tv_right), R.color.colorAccent);
        universalDialog.setOnDialogItemClickListener(new UniversalDialog.OnDialogItemClickListener() {
            @Override
            public void OnDialogItemClick(UniversalDialog dialog, View view) {
                switch (view.getId()) {
                    case R.id.tv_left:

                        break;
                    case R.id.tv_right:

                        break;
                }
            }
        });
    }

}

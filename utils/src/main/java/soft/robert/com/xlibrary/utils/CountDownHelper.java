package soft.robert.com.xlibrary.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created on 2018/1/3.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class CountDownHelper extends CountDownTimer {

    private  int defalutColor;
    private  String tips;//默认提示语
    private  long duration;//倒计时时长
    private  TextView textView;//目标控件
    private  GradientDrawable mDefaultBackground;//默认样式
    private  int changedColor;//点击后变色
    private boolean isFirst = true;

    @SuppressLint("NewApi")
    public CountDownHelper(TextView textView, int defaultColorId, int changedColorId, long duration, long graded) {
        super(duration, graded);
        if (textView == null){
            return;
        }
        //默认样式
        mDefaultBackground = ((GradientDrawable) textView.getBackground());
        this.textView = textView;
        tips = textView.getText().toString();
        this.duration = duration;
        defalutColor = textView.getResources().getColor(defaultColorId);
        changedColor = textView.getResources().getColor(changedColorId);
    }




    @Override
    public void onTick(long l) {
        //计时过程
        if(isFirst){//开始计时
            mDefaultBackground.setColor(changedColor);
            mDefaultBackground.setStroke(0,0);
            isFirst = false;
        }
        textView.setClickable(false);//禁止点击
        textView.setText(l/1000+"秒后重发");
    }

    @Override
    public void onFinish() {
        //计时完毕
        mDefaultBackground.setColor(defalutColor);
        mDefaultBackground.setStroke(2,2);
        textView.setClickable(true);
        textView.setText(tips);
    }
}

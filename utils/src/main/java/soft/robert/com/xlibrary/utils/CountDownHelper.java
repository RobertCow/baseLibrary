package soft.robert.com.xlibrary.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created on 2018/1/3.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class CountDownHelper extends CountDownTimer {

    private  String mTips;//默认提示语
    private  long mDuration;//倒计时时长
    private  TextView mTextView;//目标控件
    private  Drawable mDefaultBackground,mChangedDrawable;//默认样式
    private  int mChangedTextColor;//点击后变色
    private boolean isFirst = true;
    private  int mDefaultTextColor;

    @SuppressLint("NewApi")
    public CountDownHelper(TextView textView, Drawable changedDrawable, int changedTextColorId, long duration, long graded) {
        super(duration, graded);
        if (textView == null){
            return;
        }
        //默认样式
        mDefaultBackground = textView.getBackground();
        mDefaultTextColor = textView.getCurrentTextColor();
        mChangedDrawable = changedDrawable;
        mTextView = textView;
        mTips = textView.getText().toString();
        mChangedTextColor = textView.getResources().getColor(changedTextColorId);
    }




    @Override
    public void onTick(long l) {
        if (mTextView==null)
            return;
        //计时过程
        if(isFirst){//开始计时
            mTextView.setBackgroundDrawable(mChangedDrawable);
            mTextView.setTextColor(mChangedTextColor);
            isFirst = false;
        }
        mTextView.setClickable(false);//禁止点击
        mTextView.setText(l/1000+"秒后重发");
    }

    @Override
    public void onFinish() {
        if (mTextView==null)
            return;
        //计时完毕
        mTextView.setTextColor(mDefaultTextColor);
        mTextView.setBackgroundDrawable(mDefaultBackground);
        mTextView.setClickable(true);
        mTextView.setText(mTips);
    }
}

package ag.yinglingedu.com.xlibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by CC on 2017/5/25.
 */

public class ChangeUtil {
    private static Context mContext;
    private static Resources mResource;

    /**
     * 初始化
     * @param context
     */
    public static void initialize(Context context){
        mContext = context;
        mResource = mContext.getResources();
    }

    /**
     * 改变字体颜色
     * @param textView
     * @param colorId
     */
    public static void changeTextColor(TextView textView,int colorId){
        textView.setTextColor(ContextCompat.getColor(mContext,colorId));
    }

    /**
     * 改变Textview背景颜色
     * @param textView
     * @param colorId
     */
    public static void changeTextViewBackgroundColor(TextView textView,int colorId){
        textView.setBackgroundColor(ContextCompat.getColor(mContext,colorId));
    }

    /**
     * 改变TextView背景
     */
    public static void changeTextViewBackground(TextView textView,int drawableId){
        textView.setBackground(ContextCompat.getDrawable(mContext,drawableId));
    }

    /**
     * 改变View背景颜色
     * @param view
     * @param colorId
     */
    public static void changeViewBackgroundColor(View view,int colorId){
        view.setBackgroundColor(mContext.getColor(colorId));
    }

    /**
     * 改变Imageview src图片
     * @param imageView
     * @param sourceId
     */
    public static void setImageWithID(ImageView imageView, int sourceId){
        imageView.setImageResource(sourceId);
    }

    /**
     * 改变Imageview背景图片
     * @param imageView
     * @param sourceId
     */
    public static void setBasckgound(ImageView imageView, int sourceId){
        imageView.setBackground(ContextCompat.getDrawable(mContext,sourceId));
    }
}

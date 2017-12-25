package soft.robert.com.xlibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;

import soft.robert.com.utils.R;


public class SupportMultipleScreensUtil {

    public static final int BASE_SCREEN_WIDTH = 750;
    public static final int BASE_SCREEN_HEIGHT = 1334;
    public static final float BASE_SCREEN_WIDTH_FLOAT = 750F;
    public static final float BASE_SCREEN_HEIGHT_FLOAT = 1334F;
    public static float scale = 1.0F;
    public static float scaleH = 1.0F;

    public SupportMultipleScreensUtil() {

    }

    public static void init(Context context) {
        Resources resources=context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        scale = (float)widthPixels / BASE_SCREEN_WIDTH_FLOAT;
        scaleH = (float)heightPixels / BASE_SCREEN_HEIGHT_FLOAT;
    }

    /**
     * 针对横屏适配
     * @param context
     * @param spec
     */
    public static void init(Context context,boolean spec) {
        Resources resources=context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        scale = (float)widthPixels / BASE_SCREEN_WIDTH_FLOAT;
        scaleH = (float)heightPixels / BASE_SCREEN_HEIGHT_FLOAT;
        if(spec){
            scale = widthPixels/BASE_SCREEN_HEIGHT_FLOAT;
            scaleH = heightPixels/BASE_SCREEN_WIDTH_FLOAT;
        }
    }


    public static void scale(View view) {
        if(null != view) {
            if(view instanceof ViewGroup) {
                scaleViewGroup((ViewGroup)view);
            } else {
                scaleView(view);
            }
        }
    }

    private static void scaleView(View view) {
        Object isScale = view.getTag(R.id.is_scale_size_tag);
        if (!(isScale instanceof Boolean) || !((Boolean) isScale).booleanValue()) {
            if (view instanceof TextView) {
                scaleTextView((TextView) view);
            } else{
                scaleViewSize(view);
            }
            view.setTag(R.id.is_scale_size_tag, Boolean.valueOf(true));
        }
    }


    private static void scaleViewGroup(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); ++i) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                scaleViewGroup((ViewGroup) view);
            }
            scaleView(view);
        }
    }


    /**
     * 巨人:
     * 谷哥的小弟
     * 肩膀上的我
     * 博客地址:
     * http://blog.csdn.net/lfdfhl
     */
    public static void scaleViewSize(View view) {
        if (null != view) {
            int paddingLeft = getScaleValue(view.getPaddingLeft(),false);
            int paddingTop = getScaleValue(view.getPaddingTop(),true);
            int paddingRight = getScaleValue(view.getPaddingRight(),false);
            int paddingBottom = getScaleValue(view.getPaddingBottom(),true);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

            LayoutParams layoutParams = view.getLayoutParams();

            if (null != layoutParams) {

                if (layoutParams.width > 0) {
                    layoutParams.width = getScaleValue(layoutParams.width,false);
                }

                if (layoutParams.height > 0) {
                    layoutParams.height = getScaleValue(layoutParams.height,true);
                }

                if (layoutParams instanceof MarginLayoutParams) {
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                    int topMargin = getScaleValue(marginLayoutParams.topMargin,true);
                    int leftMargin = getScaleValue(marginLayoutParams.leftMargin,false);
                    int bottomMargin = getScaleValue(marginLayoutParams.bottomMargin,true);
                    int rightMargin = getScaleValue(marginLayoutParams.rightMargin,false);
                    marginLayoutParams.topMargin = topMargin;
                    marginLayoutParams.leftMargin = leftMargin;
                    marginLayoutParams.bottomMargin = bottomMargin;
                    marginLayoutParams.rightMargin = rightMargin;
                }
            }
            view.setLayoutParams(layoutParams);
        }
    }

    private static void setTextViewCompoundDrawables(TextView textView, Drawable leftDrawable, Drawable topDrawable, Drawable rightDrawable, Drawable bottomDrawable) {
        if(null != leftDrawable) {
            scaleDrawableBounds(leftDrawable);
        }

        if(null != rightDrawable) {
            scaleDrawableBounds(rightDrawable);
        }

        if(null != topDrawable) {
            scaleDrawableBounds(topDrawable);
        }

        if(null != bottomDrawable) {
            scaleDrawableBounds(bottomDrawable);
        }

        textView.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
    }

    public static Drawable scaleDrawableBounds(Drawable drawable) {
        int right=getScaleValue(drawable.getIntrinsicWidth(),false);
        int bottom=getScaleValue(drawable.getIntrinsicHeight(),true);
        drawable.setBounds(0, 0, right, bottom);
        return drawable;
    }

    public static void scaleTextView(TextView textView) {
        if (null != textView) {
            scaleViewSize(textView);
            Object isScale = textView.getTag(R.id.is_scale_font_tag);
            if (!(isScale instanceof Boolean) || !((Boolean) isScale).booleanValue()) {
                float size = textView.getTextSize();
                size *= scaleH;
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            }

            Drawable[] drawables = textView.getCompoundDrawables();
            Drawable leftDrawable = drawables[0];
            Drawable topDrawable = drawables[1];
            Drawable rightDrawable = drawables[2];
            Drawable bottomDrawable = drawables[3];
            setTextViewCompoundDrawables(textView, leftDrawable, topDrawable, rightDrawable, bottomDrawable);
            int compoundDrawablePadding = getScaleValue(textView.getCompoundDrawablePadding(),false);
            textView.setCompoundDrawablePadding(compoundDrawablePadding);
        }
    }

    public static int getScaleValue(int value,boolean isV) {
        if(isV){
            return value <= 4?value:(int) Math.ceil((double)(scaleH * (float)value));
        }
        return value <= 4?value:(int) Math.ceil((double)(scale * (float)value));
    }
}

package soft.robert.com.xlibrary.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import soft.robert.com.utils.R;
import soft.robert.com.xlibrary.utils.SupportMultipleScreensUtil;

/**
 * Created on 2017/12/28.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class UniversalDialog extends Dialog implements View.OnClickListener{
    private Context context;      // 上下文
    private int layoutResID;      // 布局文件id
    private int gravity;          //Dialog位置
    private int[] listenedItems;  // 要监听的控件id

    public UniversalDialog(@NonNull Context context, int layoutResID, int gravity, int[] listenedItems) {
        super(context, R.style.dialog_custom); //dialog的样式
        this.context = context;
        this.layoutResID = layoutResID;
        this.gravity = gravity;
        this.listenedItems = listenedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(gravity); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画效果
        View view = LayoutInflater.from(context).inflate(layoutResID,null);
        SupportMultipleScreensUtil.scale(view);//缩放布局，适配UI
        setContentView(view);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*4/5; // 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);// 点击Dialog外部消失
        //遍历控件id,添加点击事件
        for (int id : listenedItems) {
            findViewById(id).setOnClickListener(this);
        }
    }

    private OnDialogItemClickListener listener;
    public interface OnDialogItemClickListener {
        void OnDialogItemClick(UniversalDialog dialog, View view);
    }
    public void setOnDialogItemClickListener(OnDialogItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        dismiss();
        listener.OnDialogItemClick(this,view);
    }
}

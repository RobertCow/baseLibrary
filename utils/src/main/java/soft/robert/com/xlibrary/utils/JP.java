package soft.robert.com.xlibrary.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析json，成功，返回对应实例；失败，显示错误信息并返回null
 * Created by M 4700 on 2017/7/14.
 */

public class JP {

    private static JP jp;
    private String msg = "null";
    private static int code = -1;
    public static boolean isOK = false;


    private JP() {
    }

    /**
     * 单例
     * @return
     */
    public static JP getInstance() {
        if (jp == null) {
            jp = new JP();
        }
        return jp;
    }

    /**
     * 解析json
     *
     * @param json
     * @param classOfT
     * @return
     */
    public <T> T parse(String json, Class<T> classOfT) {
        try {
            if (isOK(json)) {
                T t = new Gson().fromJson(json, classOfT);
                return classOfT.cast(t);
            } else {
                showMsg();
            }
        } catch (Exception e) {
            ToastUtils.showShortToast("解析失败");
        }
        return null;
    }

    /**
     * 获取返回信息
     *
     * @return
     */
    private void showMsg() {
        ToastUtils.showShortToast(msg);
    }

    /**
     * 判断请求是否成功
     *
     * @param result
     * @return
     */
    public  boolean isOK(String result) {
        code = -1;
        try {
            JSONObject jsonObject = new JSONObject(result);
            code = jsonObject.getInt("code");
            msg = jsonObject.getString("msg");
            if (code == 0) {
                isOK = true;
                return true;
            } else {
                isOK = false;
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 返回Code
     *
     * @return
     */
    public  int getCode() {
        return code;
    }

}

package soft.robert.com.xlibrary.utils;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocketListener;

/**
 * Created by wulei
 * modified by chenpengfei
 * Data: 2016/8/3.
 */
public class RequestUtil {


    /**
     * 推送json给服务器
     *
     * @param callback
     * @param url
     * @param json
     * @param id
     */
    public void postJson(StringCallback callback, String url, String json, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
        PostStringBuilder params1 = OkHttpUtils
                .postString()
                .url(url)
                .id(id)
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"));
        String token = Utils.getSpUtils().getString("token");
        if (!TextUtils.isEmpty(token)) {
            params1.addHeader("accessToken", token);
        }
        params1.build().execute(callback);
    }

    /**
     * Post请求
     *
     * @param callback
     * @param url
     * @param params
     * @param id
     */
    public void post(StringCallback callback, String url, Map params, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
        OkHttpUtils
                .post()
                .url(url)
                .id(id)
                .tag(id)
                .params(params)
                .build().execute(callback);
    }

    /**
     * Post请求
     *
     * @param callback
     * @param url
     * @param params
     * @param id
     */
    public void postHeader(StringCallback callback, String url, Map params, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
        PostFormBuilder params1 = OkHttpUtils
                .post()
                .url(url)
                .id(id)
                .tag(id)
                .params(params);
        String userId = Utils.getSpUtils().getString(C.UserId);
        String createTime = Utils.getSpUtils().getString(C.CreateTime);
        String lastLoginTime = Utils.getSpUtils().getString(C.LastLoginTime);
        if (!TextUtils.isEmpty(userId)) {
            params1.addHeader(C.CreateTime, createTime);
            params1.addHeader(C.LastLoginTime, lastLoginTime);
            params1.addHeader(C.UserId, userId);
        }
        params1.build().execute(callback);
    }

    /**
     * 上传json到服务器
     * @param callback
     * @param url
     * @param json
     * @param id
     */
 public void upLoadJsonHeader(StringCallback callback, String url, String json, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
     PostStringBuilder postStringBuilder = OkHttpUtils
             .postString()
             .url(url)
             .id(id)
             .tag(id)
             .content(json)
             .mediaType(MediaType.parse("application/json; charset=utf-8"));

     String token = Utils.getSpUtils().getString("token");
        if (!TextUtils.isEmpty(token)) {
            postStringBuilder.addHeader("accessToken", token);
        }
        postStringBuilder.build().execute(callback);
    }


    /**
     * Get请求
     *
     * @param callback
     * @param url
     * @param id
     */
    public void get(StringCallback callback, String url, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
        OkHttpUtils
                .get()
                .url(url)
                .id(id)
                .tag(id)
                .build().execute(callback);
    }


    /**
     * 上传单个文件
     *
     * @param callback
     * @param url
     * @param key
     * @param file
     * @param id
     */
    public void upLoad(StringCallback callback, String url, String key, File file, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
        OkHttpUtils.post()//
                .addFile(key, file.getName(), file)//
                .url(url)
                .id(id)
                .tag(id)
                .build()//
                .execute(callback);
    }

    /**
     * 上传单个文件，带参数
     *
     * @param callback
     * @param url
     * @param key
     * @param file
     * @param id
     */
    public void upLoad(StringCallback callback, String url, String key, File file, Map<String, String> map, int id) {
        if(!NetWorkUtils.isMobileConnected(Utils.getContext())){
            ToastUtils.showShortToast("网络错误！");
            return;
        }
        PostFormBuilder params1 = OkHttpUtils
                .post()
                .addFile(key, file.getName(), file)//
                .url(url)
                .id(id)
                .tag(id)
                .params(map);
        String token = Utils.getSpUtils().getString("token");
        if (!TextUtils.isEmpty(token)) {
            params1.addHeader("accessToken", token);
        }
        params1.build().execute(callback);
    }

    /**
     * 长连接，实时数据
     * @param url
     * @param listener
     */
    public void connectWebSocket(String url,WebSocketListener listener){
        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newWebSocket(request, listener);
    }

    /**
     * 下载文件
     * @param url
     * @param fileCallBack
     */
    public void downLoadFile(String url, FileCallBack fileCallBack){
        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(fileCallBack);
    }

    /**
     * 本地缓存
     * @param key
     * @param value
     */
    public void save(String key,String value){
       Utils.getSpUtils().put(key,value);
    }

    /**
     * 本地缓存
     * @param key
     * @param value
     */
    public void save(String key,long value){
       Utils.getSpUtils().put(key,value);
    }

    /**
     * 本地缓存
     * @param key
     * @param value
     */
    public void save(String key,boolean value){
       Utils.getSpUtils().put(key,value);
    }

    /**
     * 本地缓存
     * @param key
     * @param value
     */
    public void save(String key,int value){
       Utils.getSpUtils().put(key,value);
    }

    /**
     * 本地缓存
     * @param key
     * @param value
     */
    public void save(String key,float value){
       Utils.getSpUtils().put(key,value);
    }

    /**
     * 清空缓存
     */
    public void clear(){
        Utils.getSpUtils().clear();
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public boolean getBoolean(String key){
        return Utils.getSpUtils().getBoolean(key,false);
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public String getString(String key){
        return Utils.getSpUtils().getString(key,"null");
    }
}

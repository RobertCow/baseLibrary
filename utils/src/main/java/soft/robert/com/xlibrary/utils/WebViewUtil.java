package soft.robert.com.xlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by M 4700 on 2017/8/16.
 */

public class WebViewUtil {

    public final static String CSS_STYLE ="<style>* {font-size:30px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:310px;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";
    /**
     * @param url 要打开html的路径
     * @param web WebView控件
     */
    public static void getHtml(String url, WebView web) {
        initSetting(web);

        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse(url);
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if(uri.getScheme().equals("http")||uri.getScheme().equals("https")){
                    view.loadUrl(url);
//                    LogUtil.e("-------Url------");
                    return false;
                }
                else{
                    try {
                        Intent intent;
                        intent = Intent.parseUri(url,
                                Intent.URI_INTENT_SCHEME);
                        intent.addCategory("android.intent.category.BROWSABLE");
                        intent.setComponent(null);
                        intent.setSelector(null);
                        view.getContext().startActivity(intent);
                    LogUtil.e("-----------content-----------");
                    } catch (Exception e) {

                    }
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) { //http和https协议开头的执行正常的流程
                    LogUtils.e("-----------正常should-----------"+url);
//                    if(url.contains("scheme")){
//                        try {
//                            Intent intent;
//                            intent = Intent.parseUri(url,
//                                    Intent.URI_INTENT_SCHEME);
//                            intent.addCategory("android.intent.category.BROWSABLE");
//                            intent.setComponent(null);
//                            intent.setSelector(null);
//                            view.getContext().startActivity(intent);
//                            LogUtil.e("-----------content-----------");
//                        } catch (Exception e) {
//
//                        }
//                        return null;
//                    }
                    return super.shouldInterceptRequest(view, url);
                } else { //其他的URL则会开启一个Acitity然后去调用原生APP
//                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    view.getContext().startActivity(in);
//                    LogUtils.e("-----------QQ-----------"+url);
                    return null;
                }
            }
        });
    }

    public static void loadPDF(String url,WebView mWebView){
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setSupportZoom(true);

        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.getSettings().setAllowFileAccess(true);

//        mWebView.getSettings().setPluginsEnabled(true);

        mWebView.getSettings().setUseWideViewPort(true);//----------------------------

        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.requestFocus();

        mWebView.getSettings().setLoadWithOverviewMode(true);//--------------------------

        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        String data = "<iframe src='https://docs.google.com/gview?embedded=true&url="+url+"'"+" width='100%' height='100%' style='border: none;'></iframe>";

        mWebView.loadData(data, "text/html", "UTF-8");
//        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
    }

    // 设置cookie
    public static void syncCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        CookieSyncManager.getInstance().sync();
    }

    private static void initSetting(WebView web) {
        // TODO Auto-generated method stub
        WebSettings settings = web.getSettings();
        // 是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setJavaScriptEnabled(true);
        /*
         * LOAD_DEFAULT设置如何缓存 默认使用缓存，当缓存没有，或者缓存过期，才使用网络
         * LOAD_CACHE_ELSE_NETWORK 设置默认使用缓存，即使是缓存过期，也使用缓存
         * 只有缓存消失，才使用网络
         */
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        //是否展示一个缩放按钮（）
        settings.setBuiltInZoomControls(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        web.getSettings().setSupportZoom(true);// 是否可以缩放，默认true
        web.getSettings().setBuiltInZoomControls(true);// 是否显示缩放按钮，默认false必须要加，不然缩放不起作用
        web.setVerticalScrollBarEnabled(false);
        web.setHorizontalScrollBarEnabled(false);//隐藏滚动条
        web.getSettings().setDisplayZoomControls(false); //不显示webview缩放按钮
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setUseWideViewPort(true);// 设置此属性，可任意比例缩放。大视图模式
        web.getSettings().setLoadWithOverviewMode(true);// 和setUseWideViewPort(true)一起解决网页自适应问题
        web.getSettings().setAppCacheEnabled(true);// 是否使用缓存
        web.getSettings().setDomStorageEnabled(true);// DOM Storage
        // w.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用

    }



    public static void showContent(String str, WebView web) {
        initSetting(web);
        web.loadDataWithBaseURL("", CSS_STYLE + str, "text/html", "utf-8", null);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    private static String fmtString(String str) {
        String notice = "";
        try {
            notice = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException ex) {
        }
        return notice;
    }

}

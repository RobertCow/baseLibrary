package soft.robert.com.xlibrary.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by M 4700 on 2017/7/14.
 */

public class CrashUtil {
    public static void install(){
        Cockroach.install(new Cockroach.ExceptionHandler() {
            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                //开发时使用Cockroach可能不容易发现bug，所以建议开发阶段在handlerException中用Toast谈个提示框，
                //由于handlerException可能运行在非ui线程中，Toast又需要在主线程，所以new了一个new Handler(Looper.getMainLooper())，
                //所以千万不要在下面的run方法中执行耗时操作，因为run已经运行在了ui线程中。
                //new Handler(Looper.getMainLooper())只是为了能弹出个toast，并无其他用途
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //建议使用下面方式在控制台打印异常，这样就可以在Error级别看到红色log
                            LogUtils.e("--------------------AndroidRuntime"+"--->CockroachException:"+thread+"<---",throwable);
//                            ToastUtils.showShortToast(throwable.toString(), Toast.LENGTH_SHORT);
//                        throw new RuntimeException("..."+(i++));
                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });
    }

    public static void unInstall(){
        Cockroach.uninstall();
    }
}

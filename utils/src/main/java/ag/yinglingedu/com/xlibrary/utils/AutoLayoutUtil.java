package ag.yinglingedu.com.xlibrary.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by CC on 2017/5/24.
 */

public class AutoLayoutUtil {
    private static String mDirName;
    public static   void getDp(int height,int width,float screenInch,String dirName) {
        mDirName = dirName;
        /*int width = 1080;//屏幕宽度
        int height = 1920;//屏幕高度
        float screenInch = 5.0f;//屏幕尺寸*/
//设备密度公式
        float density = (float) Math.sqrt(width * width + height * height) / screenInch / 160;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (int px = 0; px <= 1000; px += 2) {
            //像素值除以density
            String dp = px * 1.0f / density + "";
            //拼接成资源文件的内容，方便引用
            if (dp.indexOf(".") + 4 < dp.length()) {//保留3位小数
                dp = dp.substring(0, dp.indexOf(".") + 4);
            }
            stringBuilder.append("<dimen name=\"px").append(px + "").append("dp\">").append(dp).append("dp</dimen>\n");
        }
        stringBuilder.append("</resources>");
        saveFile(stringBuilder.toString());
    }

    private static void saveFile(String str) {
        String filePath = null;
        filePath = "./app/src/main/res" + File.separator + mDirName +"/dimens.xml";
       /*  boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
       if (hasSDCard) { // SD卡根目录的px2dp.text
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "values-1920x1080.xml";
        } else  // 系统下载缓存根目录的px2dp.text
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + "px2dp.txt";*/

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] a){
//        getDp();
    }
}

package moe.div.mohttp;

import android.util.Log;

import java.util.TreeMap;

/**
 * @author 林墨
 * @time 20/2/21  21:41
 * @desc 打印日志用，方便发布时关闭
 */
public class MoHttpLog {

    public static boolean open = true;

    public static void debug(boolean debug){
        open = debug;
    }

    public static void i(String msg){
        if(!open){
            return;
        }
        Log.i("MoHttp--", msg);
    }

}

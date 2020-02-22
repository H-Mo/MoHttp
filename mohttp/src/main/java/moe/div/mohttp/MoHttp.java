package moe.div.mohttp;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author 林墨
 * @time 20/2/21  21:33
 * @desc MoHttp请求核心类
 */
public class MoHttp {

    private static OkHttpClient mClient;

    private static MoHttp mInstance;

    private MoHttp(){}

    public static MoHttp getInstance(){
        if(mInstance == null){
            synchronized (MoHttp.class){
                if(mInstance == null){
                    mInstance = new MoHttp();
                    mClient = new OkHttpClient.Builder().build();
                }
            }
        }
        return mInstance;
    }


    /**
     * 请求网络数据，POST请求
     * @param url           请求地址
     * @param map           请求参数
     * @return              可订阅对象
     */
    public Observable<String> post(String url, Map<String,String> map){
        return post(url, map, null);
    }

    /**
     * 请求网络数据，POST请求
     * @param url           请求地址
     * @param map           请求参数
     * @param encode        指定接收数据的字符编码,常用与请求网页HTML
     * @return              可订阅对象
     */
    public Observable<String> post(String url, Map<String,String> map, String encode){
        return Observable.create(emitter -> {
            // 创建请求的参数body
            FormBody.Builder builder = new FormBody.Builder();
            // 遍历key
            if (null != map) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    MoHttpLog.i("Post:Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    builder.add(entry.getKey(), entry.getValue().toString());
                }
            }
            RequestBody body = builder.build();

            Request request = new Request.Builder().url(url).post(body).build();
            mClient.newCall(request).enqueue(new NetCallback(emitter, encode));
        });
    }

    /**
     * 请求网络数据，GET请求
     * @param url           请求地址
     * @return              可订阅对象
     */
    public Observable<String> get(String url){
        return get(url, null);
    }

    /**
     * 请求网络数据，GET请求
     * @param url           请求地址
     * @param encode        指定接收数据的字符编码,常用与请求网页HTML
     * @return              可订阅对象
     */
    public Observable<String> get(String url, String encode){
        MoHttpLog.i("Get:" + url);
        return Observable.create(
            emitter -> mClient.newCall(new Request.Builder().url(url).build())
                .enqueue(new NetCallback(emitter, encode))
        );
    }

    /**
     * 上传文件
     * @param url           请求地址
     * @param file          文件对象
     * @return              可订阅对象
     */
    public Observable<String> updateFile(String url, File file){
        return updateFile(url, file, null);
    }

    /**
     * 上传文件
     * @param url           请求地址
     * @param file          文件对象
     * @param encode        指定接收数据的字符编码,常用与请求网页HTML
     * @return              可订阅对象
     */
    public Observable<String> updateFile(String url, File file, String encode){
        return Observable.create(emitter -> {
            // 修改MINI-TYPE
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            // 创建请求主体
            RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of(
                    "Content-Disposition",
                    "form-data; name=\"file\"; filename=\"" + file.getName() + "\""), fileBody)
                .build();
            // 将请求主体添加请求中
            Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
            // 异步执行请求
            mClient.newCall(request).enqueue(new NetCallback(emitter, encode));
        });
    }

}

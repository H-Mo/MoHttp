package moe.div.mohttp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Emitter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author 林墨
 * @time 20/2/21  21:40
 * @desc 网络请求的回调接口
 */
public class NetCallback  implements Callback {

    private Emitter<String> emitter;
    private String encode;

    NetCallback(Emitter<String> emitter, String encode){
        this.emitter = emitter;
        this.encode = encode;
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        emitter.onError(e);
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if(response.isSuccessful()){
            String data = "";
            if(encode == null || "".equals(encode)){
                data = response.body().string();
            }else {
                byte[] b = response.body().bytes();  // 获取数据的bytes
                data = new String(b, encode);        // 转换编码
            }
            emitter.onNext(data);
        }else {
            emitter.onError(new RuntimeException(response.message()));
        }
    }

}

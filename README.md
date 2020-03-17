# MoHttp
[ ![Download](https://api.bintray.com/packages/linmo789/MoUtils/MoHttp/images/download.svg) ](https://bintray.com/linmo789/MoUtils/MoHttp/_latestVersion)

这是个对OKHttp进行简要封装而成的库，仅用于简化请求网络资源时的工作量。

### 引用关系

```
// 网络请求
api "com.squareup.okhttp3:okhttp:4.4.0"
// RxJava
api 'io.reactivex.rxjava2:rxjava:2.2.2'
api 'io.reactivex.rxjava2:rxandroid:2.1.0'
```


### 如何引用

直接在 build.gradle 中进行如下引用即可

```
implementation 'moe.div:mohttp:1.0'
```

### Get

```java
Disposable subscribe = MoHttp.getInstance()
            .get("http://baidu.com")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.i("mo--", s);
            });
```


### POST

```java
Disposable subscribe = MoHttp.getInstance().post("http://baidu.com", null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.i("mo--", s);
            });
```

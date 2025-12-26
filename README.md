# MoHttp
[ ![Download](https://api.bintray.com/packages/linmo789/MoUtils/MoHttp/images/download.svg) ](https://bintray.com/linmo789/MoUtils/MoHttp/_latestVersion)

这是个对OKHttp进行简要封装而成的库，仅用于简化请求网络资源时的工作量。


### 如何引用

直接在 build.gradle 中进行如下引用即可

```
implementation 'moe.div:mohttp:1.0'
```

### 获取MoHttp对象

执行网络请求需要先获取到MoHttp对象，再通过对象方法进行请求

```java
MoHttp instance = MoHttp.getInstance();
```

### 怎么进行Get请求

通过MoHttp对象的get()方法进行请求

```java
public Observable<String> get(String url);
public Observable<String> get(String url, String encode);
```

参数 | 说明
---|---
url | 需要请求的url
encode | 响应数据的编码格式

完整示例：

```java
Disposable subscribe = MoHttp.getInstance()
            .get("http://baidu.com")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.i("mo--", s);
            });
```

### 怎么进行POST请求

通过MoHttp对象的post()方法进行请求

```java
public Observable<String> post(String url, Map<String,String> map);
public Observable<String> post(String url, Map<String,String> map, String encode);
```

参数 | 说明
---|---
url | 需要请求的url
encode | 响应数据的编码格式

完整示例：

```java
Disposable subscribe = MoHttp.getInstance()
            .post("http://baidu.com", null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.i("mo--", s);
            });
```

### 怎么上传文件

通过MoHttp对象的updateFile()方法进行请求

```java
public Observable<String> updateFile(String url, File file);
public Observable<String> updateFile(String url, File file, String encode);
```

参数 | 说明
---|---
url | 需要请求的url
encode | 响应数据的编码格式

完整示例：

```java
File file = new File("...");    // 先创建需上传文件的File对象
Disposable subscribe = MoHttp.getInstance()
            .updateFile(file)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.i("mo--", s);
            });
```

### 对哪些库进行了依赖

```
// 网络请求
api "com.squareup.okhttp3:okhttp:4.4.0"
// RxJava
api 'io.reactivex.rxjava2:rxjava:2.2.2'
api 'io.reactivex.rxjava2:rxandroid:2.1.0'
```

### 开源协议/License

```txt
   Copyright 2020,divmoe

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

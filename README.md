# MoHttp
[ ![Download](https://api.bintray.com/packages/linmo789/MoUtils/MoHttp/images/download.svg) ](https://bintray.com/linmo789/MoUtils/MoHttp/_latestVersion)

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
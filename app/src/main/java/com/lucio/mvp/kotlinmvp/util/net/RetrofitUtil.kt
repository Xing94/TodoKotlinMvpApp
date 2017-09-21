package com.lucio.mvp.kotlinmvp.util.net

import com.lucio.mvp.kotlinmvp.AppConstant
import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.MvpApp
import com.lucio.mvp.kotlinmvp.base.BaseBean
import com.lucio.mvp.kotlinmvp.base.BaseView
import com.lucio.mvp.kotlinmvp.data.source.remote.MainService
import com.lucio.mvp.kotlinmvp.util.LogUtil
import com.lucio.mvp.kotlinmvp.util.retrofit.gson.GsonConverterFactory
import com.lucio.mvp.kotlinmvp.util.retrofit.rxjava2.RxJava2CallAdapterFactory
import com.google.gson.Gson

import java.io.IOException
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit

/**
 * Retrofit工具类
 */
object RetrofitUtil {

    private var retrofit: Retrofit? = null

    fun setBaseUrl(baseUrl: String) {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(MvpApp.client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    val instance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(AppConstant.host)
                        .client(MvpApp.client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return retrofit!!
        }

    val mainService: MainService
        get() = RetrofitUtil.instance.create(MainService::class.java)

    /**
     * 请求方法：只能运行在子线程中，不会阻塞UI线程

     * @param observable 观察者
     * *
     * @param callback   已封装的请求回调
     * *
     * @param <T>        数据模型
    </T> */
    fun <T> request(mView: BaseView<*>, observable: Observable<T>, callback: NetCallBack<T>) {

        object : Thread() {
            override fun run() {
                observable.subscribeOn(Schedulers.io())
                observable.unsubscribeOn(Schedulers.io())
                observable.subscribe({ t ->
                    println("onNext")
                    var bean: BaseBean? = null
                    if (t is ResponseBody) {
                        val responseBody = t as ResponseBody
                        try {
                            bean = GsonJson(responseBody.string(), BaseBean::class.java)
                            LogUtil.i("RetrofitUtil Json", responseBody.string())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    } else {
                        try {
                            bean = t as BaseBean
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                    if (bean != null) {
                        val code = bean.reCode
                        if ("0" == code!!.trim { it <= ' ' }) {
                            callback.onSuccess(t)
                        } else {
                            callback.onFailed(mView, bean)
                        }
                    } else {
                        mView.showToast(R.string.request_error)
                    }
                }, { throwable -> callback.onError(mView, throwable) },
                        { callback.onCompleted() })
            }
        }.start()
    }


    //    public static <T> void requestConsumer(Observable<T> observable, final NetCallBack<T> callback) {
    //
    //        observable.subscribeOn(Schedulers.io());
    ////        observable.unsubscribeOn(Schedulers.io());
    //        observable.observeOn(AndroidSchedulers.mainThread());
    //        observable.subscribe(t -> {
    //            System.out.println("onNext");
    //            BaseBean bean = null;
    //            if (t instanceof ResponseBody) {
    //                ResponseBody responseBody = (ResponseBody) t;
    //                try {
    //                    bean = GsonJson(responseBody.string(), BaseBean.class);
    //                    LogUtil.i("RetrofitUtil Json", responseBody.string());
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //            } else {
    //                try {
    //                    bean = (BaseBean) t;
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //            if (bean != null) {
    //                String code = bean.getReCode();
    //                if ("0".equals(code.trim())) {
    //                    callback.onSuccess(t);
    //                } else {
    //                    callback.onFailed(bean);
    //                }
    //            } else {
    //                ToastUtil.show(R.string.request_error);
    //            }
    //        }, throwable -> {
    //            callback.onError(throwable);
    //        }, () -> {
    //            callback.onCompleted();
    //        });
    //    }


    //控制下拉列表的刷新事件
    //    public static <T> void request(final TwinklingRefreshLayout refreshLayout,
    //                                   Observable<T> observable, final CallBackIng<T> callback) {
    //        observable.subscribeOn(Schedulers.io())
    //                .unsubscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(new Subscriber<T>() {
    //                    @Override
    //                    public void onCompleted() {
    //                        LoadingUtil.dismissLoadingDialog();
    //                        refreshLayout.finishRefreshing();
    //                        refreshLayout.finishLoadmore();
    //                        callback.onCompleted();
    //                    }
    //
    //                    @Override
    //                    public void onError(Throwable e) {
    //                        LoadingUtil.dismissLoadingDialog();
    //                        refreshLayout.finishRefreshing();
    //                        refreshLayout.finishLoadmore();
    //                        callback.onError(e);
    //                    }
    //
    //                    @Override
    //                    public void onNext(T t) {
    //                        LoadingUtil.dismissLoadingDialog();
    //                        refreshLayout.finishRefreshing();
    //                        refreshLayout.finishLoadmore();
    //                        BaseBean bean = null;
    //                        if (t instanceof ResponseBody) {
    //                            ResponseBody responseBody = (ResponseBody) t;
    //                            try {
    //                                bean = GsonJson(responseBody.string(), BaseBean.class);
    //                                LogIngUtil.i("RetrofitUtil Json", responseBody.string());
    //                            } catch (IOException e) {
    //                                e.printStackTrace();
    //                            }
    //                        } else {
    //                            try {
    //                                bean = (BaseBean) t;
    //                            } catch (Exception e) {
    //                                e.printStackTrace();
    //                            }
    //                        }
    //                        if (bean != null) {
    //                            String code = bean.getCode();
    //                            if ("0".equals(code.trim()) && bean.isIsSuccess()) {
    //                                callback.onSuccess(t);
    //                            } else {
    //                                callback.onFailed(bean);
    //                            }
    //                        } else {
    //                            ToastUtil.show(R.string.request_error);
    //                        }
    //                    }
    //                });
    //    }

    //转换为实体类
    fun <T> GsonJson(json: String, type: Class<T>): T? {
        LogUtil.i("RetrofitUtil Json", json)
        try {
            val gson = Gson()
            val obj = gson.fromJson(json, type)
            return obj
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
        }

        return null
    }

    //创建一个新的retrofit对象
    fun createRetrofit(baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(9, TimeUnit.SECONDS)

        return Retrofit.Builder().baseUrl(baseUrl).client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}

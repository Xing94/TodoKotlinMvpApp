package com.lucio.mvp.kotlinmvp.data.source.remote

import com.lucio.mvp.kotlinmvp.constants.UrlConstant
import com.lucio.mvp.kotlinmvp.data.bean.LoginBean

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Observable：打印String用ResponseBody.string
 * get参数：@Query
 * post参数：@Field
 * header：@Header
 * 可以放实体类:必须继承BaseBean
 * FormUrlEncoded 表单编码，在post请求有多个参数时使用
 */

interface MainService {

    @POST(UrlConstant.login)
    @FormUrlEncoded
    fun login(@Field("phone") phone: String, @Field("loginPwd") loginPwd: String): Observable<LoginBean>

}

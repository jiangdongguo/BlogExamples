package com.jiangdg.retrofit2.model

import com.jiangdg.retrofit2.model.bean.BaseRsp
import com.jiangdg.retrofit2.model.bean.GetArticleListRsp
import com.jiangdg.retrofit2.model.bean.GetFriendWebSiteRsp
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: jiangdg
 * date: 2020/9/28 11:04 AM
 * description: MVVM的M层，使用Retrofit2请求网络数据
 */
object WanRepository {
    private const val TAG = "WanRepository"
    private const val BASE_URL = "https://www.wanandroid.com"

    private val mJsonRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // 设置base url
            .addConverterFactory(GsonConverterFactory.create())         // 支持将响应数据转为JSON格式
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // 支持RxJava2
            .build()
    }

    private val mWanService by lazy {
        mJsonRetrofit.create(IWanService::class.java)
    }

    fun getFriendWebSiteCall(): Call<GetFriendWebSiteRsp> = mWanService.getFriendWebSite()

    fun getArticles(page: Int): Observable<GetArticleListRsp> = mWanService.getArticles(page)

    fun registerAccount(userName: String, password: String,
                 rePassword: String): Observable<BaseRsp> {
        val map = hashMapOf(
            "username" to userName,
            "password" to password,
            "repassword" to rePassword
        )
        return mWanService.registerAccount(map)
    }

    fun loginAccount(userName: String, password: String): Observable<BaseRsp> {
        return mWanService.loginAccount(userName, password)
    }
}
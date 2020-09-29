package com.jiangdg.retrofit2.model

import com.jiangdg.retrofit2.model.bean.BaseRsp
import com.jiangdg.retrofit2.model.bean.GetArticleListRsp
import com.jiangdg.retrofit2.model.bean.GetFriendWebSiteRsp
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * author: jiangdg
 * date: 2020/9/28 11:08 AM
 * description: Retrofit HTTP请求接口
 */
interface IWanService {

    /**
     * 获取友情链接
     */
    @GET("/friend/json")
    fun getFriendWebSite(): Call<GetFriendWebSiteRsp>

    /**
     * 获取文章列表
     * @param page 第几页数据
     */
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Observable<GetArticleListRsp>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    fun registerAccount(
        @FieldMap map: Map<String, String>
    ): Observable<BaseRsp>

    /**
     * 登录
     * @param username 用户名
     * @param password 登录密码
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun loginAccount(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseRsp>
}
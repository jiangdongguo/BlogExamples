package com.jiangdg.retrofit2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiangdg.retrofit2.model.bean.GetFriendWebSiteRsp
import com.jiangdg.retrofit2.model.WanRepository
import com.jiangdg.retrofit2.model.bean.Article
import com.jiangdg.retrofit2.model.bean.FriendWebSite
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * author: jiangdg
 * date: 2020/9/28 10:53 AM
 * description: MVVM的VM层，用于处理业务逻辑和通知V层更新
 */
class WanViewModel : ViewModel() {

    private var mArticleDispose: Disposable? = null
    private var mLoginDispose: Disposable? = null

    val mArticlesLiveData = MutableLiveData<List<Article>>()
    val mFriendWebSiteLiveData = MutableLiveData<List<FriendWebSite>>()
    val mLoginLiveData = MutableLiveData<Boolean>()

    /**
     *  获取wanAndroid友情网址列表
     */
    fun getFriendWebSites() {
        val call = WanRepository.getFriendWebSiteCall()
        call.enqueue(object : Callback<GetFriendWebSiteRsp> {
            override fun onFailure(call: Call<GetFriendWebSiteRsp>, t: Throwable) {
                Log.e(TAG, "get friend web site failed.", t)
                mFriendWebSiteLiveData.postValue(null)
            }

            override fun onResponse(
                call: Call<GetFriendWebSiteRsp>,
                response: Response<GetFriendWebSiteRsp>
            ) {
                if (! response.isSuccessful) {
                    Log.e(TAG, "get friend web site failed.code=${response.code()},msg=${response.message()}")
                    mFriendWebSiteLiveData.postValue(null)
                    return
                }
                mFriendWebSiteLiveData.postValue(response.body()?.data)
            }
        })
    }

    /**
     * 获取wanAndroid文章列表
     *
     * @param page 第几页
     */
    fun getArticles(page: Int) {
        mArticleDispose = WanRepository.getArticles(page)
            .subscribeOn(Schedulers.io()) // 指定被观察者执行线程
            .observeOn(Schedulers.io())   // 指定观察者执行线程
            .subscribe({
                if (! it.isSuccessFull()) {
                    mArticlesLiveData.postValue(null)
                    Log.e(TAG, "get articles failed.")
                    return@subscribe
                }
                mArticlesLiveData.postValue(it.data!!.articleList)
            }, {
                Log.e(TAG, "get articles failed.", it)
                mArticlesLiveData.postValue(null)
            })
    }


    fun registerAndLogin(userName: String, password: String, rePassword: String) {
        mLoginDispose = WanRepository.registerAccount(userName, password, rePassword)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                if (! it.isSuccessFull()) {
                    Log.e(TAG, "register failed code=${it.errorCode}, msg=${it.errorMsg}")
                }
                WanRepository.loginAccount(userName, password)
            }.subscribe({ loginRsp->
                Log.e(TAG, "login status code=${loginRsp.errorCode}, msg=${loginRsp.errorMsg}")
                mLoginLiveData.postValue(loginRsp.isSuccessFull())
            }, {
                mLoginLiveData.postValue(false)
            })
    }

    override fun onCleared() {
        super.onCleared()
        mArticleDispose?.dispose()
        mLoginDispose?.dispose()
    }

    companion object {
        private const val TAG = "WanViewModel"
    }
}

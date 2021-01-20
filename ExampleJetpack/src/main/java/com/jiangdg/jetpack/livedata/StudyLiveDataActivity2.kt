package com.jiangdg.jetpack.livedata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jiangdg.jetpack.livedata.example.EventDataBus
import com.jiangdg.jetpack.livedata.example.LoginStatus
import java.lang.Exception

/**
 * author: jiangdg
 * date: 2021/1/16 11:39 AM
 * description:
 */
class StudyLiveDataActivity2: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 注册事件总线，总线名称为"LoginStatus"
         */
        // 观察者3
        EventDataBus.with<LoginStatus>(BUS_KEY).observe(this, Observer {
            Log.i("LoginStatus", "I am observer3 status = ${it.msg}")
        })

        /**发送事件
         *
         * 延迟2秒，模拟网络登录失败操作
         */
        Thread(Runnable {
            try {
                Thread.sleep(2000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.i("LoginStatus", "send login status again...")
//            EventDataBus.with<LoginStatus>(BUS_KEY).postValue(LoginStatus(-1, "login failed, account not exist", -99))
            // 改进
            EventDataBus.with<LoginStatus>(BUS_KEY).postMessage(LoginStatus(-1, "login failed, account not exist", -99))
        }).start()
    }

    companion object {

        private const val BUS_KEY = "LoginStatus"

        fun launch(context: Context) {
            context.startActivity(Intent(context, StudyLiveDataActivity2::class.java))
        }
    }
}
package com.jiangdg.jetpack.livedata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.jiangdg.jetpack.R
import com.jiangdg.jetpack.livedata.example.EventDataBus
import com.jiangdg.jetpack.livedata.example.LoginStatus
import java.lang.Exception

/**
 * author: jiangdg
 * date: 2021/1/16 11:39 AM
 * description:
 */
class StudyLiveDataActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_live_data)
        howToUseLiveData()
        howToUseMediatorLiveData()
        howToUseTransformationsMap()
        eventBusDemo()
    }

    private fun howToUseLiveData() {
        // （1）创建一个MutableLiveData对象
        val testLiveData = MutableLiveData<String>()
        // （2）使用LiveData对象注册一个观察者
        testLiveData.observe(this, Observer {
            Toast.makeText(this, "message-->$it", Toast.LENGTH_SHORT).show()
        })
        // (3) 使用LiveData对象发生消息事件
        // 如果是在异步线程更新value的值，使用postValue
        testLiveData.value = "Hello LiveData"
    }

    private fun howToUseMediatorLiveData() {
        // a. 创建两个LiveData和一个Observer
        val liveData1 = MutableLiveData<String>()
        val liveData2 = MutableLiveData<String>()
        val observer = Observer<String> {
            Toast.makeText(this, "message2-->$it", Toast.LENGTH_SHORT).show()
        }
        // b. 将两个LiveData绑定在一起
        // 统一使用一个Observer
        val mediatorLiveData = MediatorLiveData<String>()
        mediatorLiveData.addSource(liveData1, observer)
        mediatorLiveData.addSource(liveData2, observer)

        // 如果是在异步线程更新value的值，使用postValue
        liveData1.value = "Hello LiveData1"
        liveData2.value = "Hello LiveData2"
    }

    private fun howToUseTransformationsMap() {
        val loginLiveData = MutableLiveData<Boolean>()
        val newLiveData = Transformations.map(loginLiveData) { isSuccess ->
            if (isSuccess) {
                "Login success!"
            } else {
                "Login failed!"
            }
        }
        newLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        /**发送事件
         *
         * 延迟3秒，模拟网络登录成功
         */
        Thread(Runnable {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            loginLiveData.postValue(true)
        }).start()

    }

    private fun eventBusDemo() {
        /**
         * 注册事件总线，总线名称为"LoginStatus"
         */
        // 观察者1
        EventDataBus.with<LoginStatus>(BUS_KEY).observe(this, Observer {
            Log.i("LoginStatus", "I am observer1, status = ${it.msg}")
        })

        // 观察者2
        // 可以定义在其他Activity、Fragment中
        EventDataBus.with<LoginStatus>(BUS_KEY).observe(this, Observer {
            Log.i("LoginStatus", "I am observer2, status = ${it.msg}")
        })

        /**发送事件
         *
         * 延迟3秒，模拟网络登录操作
         */
        Thread(Runnable {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.i("LoginStatus", "send login status...")
//            EventDataBus.with<LoginStatus>(BUS_KEY).postValue(LoginStatus(0, "login success"))
            // 改进
            EventDataBus.with<LoginStatus>(BUS_KEY).postMessage(LoginStatus(0, "login success"))
        }).start()
    }

    fun toLiveDataActivity2(view: View) {
        StudyLiveDataActivity2.launch(this)
    }

    companion object {

        private const val BUS_KEY = "LoginStatus"

        fun launch(context: Context) {
            context.startActivity(Intent(context, StudyLiveDataActivity::class.java))
        }
    }


}
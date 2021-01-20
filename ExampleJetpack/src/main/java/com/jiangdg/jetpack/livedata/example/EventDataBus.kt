package com.jiangdg.jetpack.livedata.example

import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap

/**
 * author: jiangdg
 * date: 2021/1/16 11:40 AM
 * description: 使用LiveData实现事件总线，单例模式
 *
 * 通过代理方式实现
 */
object EventDataBus {

    private val mLiveDataMap = ConcurrentHashMap<String, LiveData<*>>()

    /** 注册事件总线
     *
     * key 表示事件总线名称
     * T 表示事件类型，泛型
     */
    fun <T> with(key: String): BusLiveData<T> {
        var liveData = mLiveDataMap[key] as? BusLiveData<T>
        if (liveData == null) {
            liveData = BusLiveData<T>(key).apply {
                mLiveDataMap[key] = this
            }
        }
        return liveData
    }

    /** 自定义LiveData
     *
     * 通过自己管理LiveData的version，便于后续同步Observer的version字段
     */
    class BusLiveData<T>(private val busName: String): MutableLiveData<T>() {
        internal var mVersion = 0

        fun sendMessage(message: T) {
            ++mVersion
            value = message
        }

        fun postMessage(message: T) {
            ++mVersion
            postValue(message)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            // 监听宿主发生销毁事件，检查LiveData是否还有其他观察者
            // 如果没有主动把LiveData移除掉
            owner.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        if (mLiveDataMap[busName]?.hasObservers() == false) {
                            mLiveDataMap.remove(busName)
                        }
                    }
                }
            })
            // 重新对observer包装
            // 使用代理observer，它会仅把最新事件分发给观察者
            super.observe(owner, ProxyObserver(this, observer))
        }
    }

    /** 代理Observer
     *
     *  通过自己管理Observer的version字段，控制是否分发事件
     */
    internal class ProxyObserver<T>(
        private val liveData: BusLiveData<T>,
        private val observer: Observer<in T>
    ): Observer<T> {
        // 初始化Observer的version与LiveData的一致
        private var mLastVersion = liveData.mVersion

        // 只有在LiveData有最新数据时，才调用Observer的onChanged分发事件
        // 其中，判断有新数据的条件时：LiveData.version > Observer.version
        override fun onChanged(data: T) {
            if (mLastVersion >= liveData.mVersion) {
                return
            }
            mLastVersion = liveData.mVersion
            observer.onChanged(data)
        }
    }
}
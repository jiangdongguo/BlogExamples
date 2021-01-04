package com.jiangdg.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver01
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver02
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver03

/**
 * author: jiangdg
 * date: 2020/11/3 4:42 PM
 * description: Activity中使用Lifecycle组件
 */
class StudyLifecycleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLifecycleObserver()
    }

    private fun addLifecycleObserver() {
        // 方法一：使用注解
//        lifecycle.addObserver(MyLifecycleObserver01())

        // 方法二： 使用LifecycleEventObserver
//        lifecycle.addObserver(MyLifecycleObserver02())

        // 方法二：使用DefaultLifecycleObserver
        lifecycle.addObserver(MyLifecycleObserver03())
    }
}
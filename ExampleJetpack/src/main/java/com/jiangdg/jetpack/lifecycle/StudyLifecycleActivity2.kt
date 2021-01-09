package com.jiangdg.jetpack.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ReportFragment
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver01
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver02
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver03

/**
 * author: jiangdg
 * date: 2020/11/3 4:42 PM
 * description: Activity中使用Lifecycle组件
 */
class StudyLifecycleActivity2: Activity(), LifecycleOwner {

    private val mLifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = mLifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ReportFragment.injectIfNeededIn(this)

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
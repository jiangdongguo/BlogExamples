package com.jiangdg.jetpack.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiangdg.jetpack.lifecycle.observer.MyLifecycleObserver03

/**
 * author: jiangdg
 * date: 2020/11/4 9:42 AM
 * description:
 */
class StudyLifecycleFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 方法一：使用注解
//        lifecycle.addObserver(MyLifecycleObserver01())

        // 方法二： 使用LifecycleEventObserver
//        lifecycle.addObserver(MyLifecycleObserver02())

        // 方法二：使用DefaultLifecycleObserver
        lifecycle.addObserver(MyLifecycleObserver03())
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
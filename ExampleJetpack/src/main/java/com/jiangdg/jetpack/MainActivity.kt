package com.jiangdg.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jiangdg.jetpack.livedata.StudyLiveDataActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toLifecycleActivity(view: View) {

    }

    fun toLiveDataActivity(view: View) {
        StudyLiveDataActivity.launch(this)
    }
}

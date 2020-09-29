package com.jiangdg.retrofit2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiangdg.retrofit2.ui.FriendWebSiteActivity
import com.jiangdg.retrofit2.ui.ShowArticlesActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btnGetFriendWebSite.setOnClickListener {
            FriendWebSiteActivity.launch(this)
        }

        btnGetArticles.setOnClickListener {
            ShowArticlesActivity.launch(this)
        }
    }

    companion object {

        fun launch(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}

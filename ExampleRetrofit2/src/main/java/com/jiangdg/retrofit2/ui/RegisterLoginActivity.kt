package com.jiangdg.retrofit2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jiangdg.retrofit2.HomeActivity
import com.jiangdg.retrofit2.R
import com.jiangdg.retrofit2.viewmodel.WanViewModel
import kotlinx.android.synthetic.main.activity_register_login.*

/**
 * author: jiangdg
 * date: 2020/9/28 2:05 PM
 * description:
 */
class RegisterLoginActivity: AppCompatActivity() {

    private val mViewModel: WanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_login)
        initView()

        mViewModel.mLoginLiveData.observe(this, Observer {
            if (! it) {
                Toast.makeText(this, "注册登录失败", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            HomeActivity.launch(this)
        })
    }

    private fun initView() {
        btnLogin.setOnClickListener {
            mViewModel.registerAndLogin(
                edt_name.text.toString(),
                edt_password.text.toString(),
                edt_repassword.text.toString()
            )
        }
    }
}
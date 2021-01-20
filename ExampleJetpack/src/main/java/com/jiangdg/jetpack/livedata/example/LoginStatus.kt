package com.jiangdg.jetpack.livedata.example

import androidx.annotation.Keep
import java.io.Serializable

/**
 * author: jiangdg
 * date: 2021/1/16 12:03 PM
 * description: 登录状态结果实体
 */
@Keep
data class LoginStatus(
    val status: Int,     // 0为登录成功，-1为登录失败
    val msg: String,     // 消息
    val errorCode: Int = 0   // 错误码
): Serializable
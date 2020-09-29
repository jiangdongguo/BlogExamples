package com.jiangdg.retrofit2.model.bean

import androidx.annotation.Keep
import java.io.Serializable

/**
 * author: jiangdg
 * date: 2020/9/28 8:36 PM
 * description:
 */
@Keep
open class BaseRsp: Serializable {
    var errorCode: Int = -1
    var errorMsg: String = ""

    fun isSuccessFull(): Boolean {
        return errorCode == 0
    }
}
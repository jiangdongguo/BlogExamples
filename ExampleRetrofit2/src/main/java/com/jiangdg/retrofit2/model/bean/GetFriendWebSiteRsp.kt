package com.jiangdg.retrofit2.model.bean

import androidx.annotation.Keep

/**
 * author: jiangdg
 * date: 2020/9/28 11:11 AM
 * description: 友情链接数据实体
 *     参考https://www.wanandroid.com/friend/json
 */
@Keep
class GetFriendWebSiteRsp: BaseRsp() {
    var data: List<FriendWebSite> = emptyList()
}

@Keep
data class FriendWebSite(
    val icon: String,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)
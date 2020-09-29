package com.jiangdg.retrofit2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiangdg.retrofit2.R
import com.jiangdg.retrofit2.model.bean.FriendWebSite

/**
 * author: jiangdg
 * date: 2020/9/28 4:01 PM
 * description:
 */
class FriendWebSiteAdapter: BaseQuickAdapter<FriendWebSite, BaseViewHolder>(R.layout.layout_friend_website_item) {

    override fun convert(holder: BaseViewHolder, item: FriendWebSite) {
        holder.setText(R.id.tvName, item.name)
        holder.setText(R.id.tvLink, item.link)
    }
}
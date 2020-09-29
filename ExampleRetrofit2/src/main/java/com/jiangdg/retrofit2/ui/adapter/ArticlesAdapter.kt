package com.jiangdg.retrofit2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiangdg.retrofit2.R
import com.jiangdg.retrofit2.model.bean.Article

/**
 * author: jiangdg
 * date: 2020/9/28 3:54 PM
 * description:
 */
class ArticlesAdapter: BaseQuickAdapter<Article, BaseViewHolder>(R.layout.layout_article_item) {
    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.setText(R.id.tvTitle, item.title)
        holder.setText(R.id.tvLink, item.link)
    }
}
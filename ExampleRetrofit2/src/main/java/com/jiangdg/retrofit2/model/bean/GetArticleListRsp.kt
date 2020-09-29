package com.jiangdg.retrofit2.model.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * author: jiangdg
 * date: 2020/9/28 11:18 AM
 * description: 文章响应数据实体，
 *      参考https://www.wanandroid.com/article/list/1/json
 */
@Keep
class GetArticleListRsp : BaseRsp() {
    var data: PageData? = null

    class PageData {
        var curPage: Int = 0

        @SerializedName("datas")
        var articleList: List<Article> = emptyList()
    }
}

@Keep
data class Article(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val desc: String,
    val id: Long,
    val link: String,
    val title: String
    // 字段太多，只截取部分
    // ...
)



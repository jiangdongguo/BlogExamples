package com.jiangdg.retrofit2.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jiangdg.retrofit2.R
import com.jiangdg.retrofit2.model.bean.Article
import com.jiangdg.retrofit2.ui.adapter.ArticlesAdapter
import com.jiangdg.retrofit2.viewmodel.WanViewModel
import kotlinx.android.synthetic.main.activity_articles.*

/**
 * author: jiangdg
 * date: 2020/9/28 2:05 PM
 * description:
 */
class ShowArticlesActivity: AppCompatActivity(), OnItemClickListener {

    private val mViewModel: WanViewModel by viewModels()
    private lateinit var mAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        initView()

        mViewModel.getArticles(1)
        mViewModel.mArticlesLiveData.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            mAdapter.setList(it)
        })
    }

    private fun initView() {
        mAdapter = ArticlesAdapter()
        mAdapter.setOnItemClickListener(this)

        articlesRV.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.line_divider,
                null)!!)
        articlesRV.addItemDecoration(divider)
        articlesRV.adapter = mAdapter
    }

    companion object {

        fun launch(context: Context) {
            context.startActivity(Intent(context, ShowArticlesActivity::class.java))
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.getItem(position)
        if (item is Article) {
            val uri = Uri.parse(item.link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
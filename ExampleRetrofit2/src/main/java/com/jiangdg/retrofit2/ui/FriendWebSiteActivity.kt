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
import com.jiangdg.retrofit2.model.bean.FriendWebSite
import com.jiangdg.retrofit2.ui.adapter.FriendWebSiteAdapter
import com.jiangdg.retrofit2.viewmodel.WanViewModel
import kotlinx.android.synthetic.main.activity_friend.*

/**
 * author: jiangdg
 * date: 2020/9/28 2:04 PM
 * description:
 */
class FriendWebSiteActivity: AppCompatActivity(), OnItemClickListener {

    private val mViewModel: WanViewModel by viewModels()
    private lateinit var mAdapter: FriendWebSiteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        initView()

        mViewModel.getFriendWebSites()
        mViewModel.mFriendWebSiteLiveData.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            mAdapter.setList(it)
        })
    }

    private fun initView() {
        mAdapter = FriendWebSiteAdapter()
        mAdapter.setOnItemClickListener(this)

        friendRV.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.line_divider,
                null)!!)
        friendRV.addItemDecoration(divider)
        friendRV.adapter = mAdapter
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, FriendWebSiteActivity::class.java))
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.getItem(position)
        if (item is FriendWebSite) {
            val uri = Uri.parse(item.link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
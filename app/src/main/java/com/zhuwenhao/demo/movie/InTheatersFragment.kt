package com.zhuwenhao.demo.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.magiepooh.recycleritemdecoration.ItemDecorations
import com.zhuwenhao.demo.R
import com.zhuwenhao.demo.utils.Constants
import kotlinx.android.synthetic.main.fragment_in_theaters.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InTheatersFragment : Fragment() {

    private lateinit var adapter: InTheatersAdapter

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        getInTheaters(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_in_theaters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        multiple_status_view.setOnRetryClickListener {
            getInTheaters(false)
        }

        swipe_refresh_layout.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh_layout.setOnRefreshListener(onRefreshListener)

        adapter = InTheatersAdapter(R.layout.item_in_theaters)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.addItemDecoration(ItemDecorations.vertical(context).type(0, R.drawable.item_decoration_h_1).create())
        recycler_view.adapter = adapter

        getInTheaters(false)
    }

    private fun getInTheaters(isRefresh: Boolean) {
        if (!isRefresh)
            multiple_status_view.showLoading()

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.DOUBAN_MOVIE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val movieApi = retrofit.create(DoubanMovieApi::class.java)
        val call = movieApi.getInTheaters("上海", 0, 20)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                if (isRefresh) {
                    swipe_refresh_layout.isRefreshing = false
                    adapter.setNewData(response!!.body()!!.subjects)
                } else {
                    multiple_status_view.showContent()
                    adapter.addData(response!!.body()!!.subjects)
                }
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                if (isRefresh)
                    swipe_refresh_layout.isRefreshing = false
                else
                    multiple_status_view.showError()
            }
        })
    }
}
package com.bhawana.flickr.FlickrModule

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.bhawana.flickr.R
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import com.bhawana.flickr.FlickrModule.Model.ListModel
import com.bhawana.flickr.FlickrModule.Model.ListModelCache
import kotlinx.android.synthetic.main.activity_flickr.*
import java.util.*

/**
 * **  Created by home on 18-Oct-18.
 */
class FlickrActivity : AppCompatActivity(),ListView {

    private val presenter = ListPresenter(this, ListIterator())
    private lateinit var mAdapter: ListAdapter
    private lateinit var mlist: MutableList<ListModel>
    private lateinit var listModelCache: ListModelCache
    private lateinit var linearLayoutManager: GridLayoutManager
    private var firstVisibleInListview: Int=0

    private var pageNumber: Int=0
    private var isLoading = false
    private var timer:Timer=Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flickr)

        listModelCache = ViewModelProviders.of(this).get(ListModelCache::class.java)
        presenter.registerSearch()

        mlist = mutableListOf()
        linearLayoutManager = GridLayoutManager(this,3)
        recycler_view.layoutManager = linearLayoutManager
        mAdapter = ListAdapter( this)
        recycler_view.adapter = mAdapter

        firstVisibleInListview = linearLayoutManager.findFirstVisibleItemPosition()

        pageNumber=1
        setUpLoadMoreListener()


        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    timer.cancel()
            }

            override fun afterTextChanged(s: Editable) {

                timer = Timer()
                timer!!.schedule(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            if(!s.toString().equals("")) {
                                mlist.clear()
                                presenter.textChanged(s.toString())
                            }

                        }
                    }
                }, 500)

            }
        })


      if (listModelCache.listModel != null) {
          mAdapter.setList(listModelCache.listModel!!)
          editText.setText(listModelCache.searchText)
        }


    }

    override fun showList(list: MutableList<ListModel>) {

    }

    override fun updateList(mlistArry: List<ListModel>) {

        mlist.addAll(mlistArry)
        listModelCache.listModel = mlist
        mAdapter.setList(mlist)
        isLoading=false
    }


    private fun setUpLoadMoreListener() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView,
                                    dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {

                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {

                        pageNumber++
                        presenter.getData(editText.text.toString(), pageNumber)
                        isLoading = true
                        firstVisibleInListview = pastVisibleItems
                    }

                }
            }
        })
    }

}

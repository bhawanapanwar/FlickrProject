package com.bhawana.flickr.FlickrModule

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhawana.flickr.FlickrModule.Model.ListModel
import com.bhawana.flickr.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import kotlinx.android.synthetic.main.adapter_flickr_list.view.*

/**
 * **  Created by home on 18-Oct-18.
 */
class ListAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    var items = mutableListOf<ListModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_flickr_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load("http://farm"+items.get(position).farm+".static.flickr.com/"+
                items.get(position).server+"/"+items.get(position).id+"_"+items.get(position).secret+".jpg")
                .transition(withCrossFade())
                .into(holder.ivImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setList(list: List<ListModel>) {
        items = list.toMutableList()
        this.notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivImage = view.imgView
}


package com.bhawana.flickr.FlickrModule

import com.bhawana.flickr.FlickrModule.Model.ListModel

/**
 * **  Created by home on 18-Oct-18.
 */
interface ListView{
    fun showList(list:MutableList<ListModel>)
    fun updateList(list:List<ListModel>)
}
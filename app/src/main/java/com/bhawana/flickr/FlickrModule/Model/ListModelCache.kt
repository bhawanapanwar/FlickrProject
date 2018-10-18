package com.bhawana.flickr.FlickrModule.Model

import android.arch.lifecycle.ViewModel

/**
 * **  Created by home on 18-Oct-18.
 */
class ListModelCache : ViewModel() {
    var listModel: List<ListModel>? = null
    var searchText : String?=null
}
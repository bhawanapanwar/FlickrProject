package com.bhawana.flickr.FlickrModule

import com.bhawana.flickr.FlickrModule.Model.ListModel
import io.reactivex.observers.DisposableObserver

/**
 * **  Created by home on 18-Oct-18.
 */
class ListObserver(private val listView:ListView) : DisposableObserver<List<ListModel>>() {

    override fun onComplete() {
        this.dispose()
    }

    override fun onNext(t: List<ListModel>) {
        listView.updateList(t)
    }

    override fun onError(e: Throwable) {

    }

}
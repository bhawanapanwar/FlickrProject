package com.bhawana.flickr.FlickrModule

import io.reactivex.subjects.PublishSubject

/**
 * **  Created by home on 18-Oct-18.
 */
class ListPresenter(var listView: ListView, val listInteractor: ListIterator) {

    private val source = PublishSubject.create<String>()

    fun getData(text:String,pageNumber: Int) {

        listInteractor.hitApi(text,pageNumber).subscribeWith(ListObserver(listView))
    }

    fun registerSearch() {
        source.flatMap {
            listInteractor.hitApi(it,1)
        }.subscribeWith(ListObserver(listView))
    }

    fun textChanged(text:String){
        source.onNext(text)
    }

}


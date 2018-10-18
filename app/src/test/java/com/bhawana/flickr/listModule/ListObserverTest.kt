package com.spotprompt.learning.listModule

import com.bhawana.flickr.FlickrModule.Model.ListModel
import com.bhawana.flickr.FlickrModule.ListObserver
import com.bhawana.flickr.FlickrModule.ListView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * **  Created by home on 18-Oct-18.
 */
@RunWith(MockitoJUnitRunner::class)
class ListObserverTest {

    @Mock
    lateinit var mockListView: ListView

    @Mock
    lateinit var mockList: List<ListModel>

    lateinit var listObserver: ListObserver


    @Before
    fun setUp() {

        listObserver = ListObserver(mockListView)
    }

    @Test
    fun should_call_updateList_when_onNext() {
        Observable.just(mockList).subscribe(listObserver)
        verify(mockListView, times(1)).updateList(mockList)
    }

}
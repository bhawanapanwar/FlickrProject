package com.spotprompt.learning.listModule

import com.bhawana.flickr.FlickrModule.ListIterator
import com.bhawana.flickr.FlickrModule.Model.ListModel
import com.bhawana.flickr.FlickrModule.ListPresenter
import com.bhawana.flickr.FlickrModule.ListView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * **  Created by home on 18-Oct-18.
 */
@RunWith(MockitoJUnitRunner::class)
class ListPresenterTest {

    @Mock
    lateinit var mockListView: ListView

    @Mock
    lateinit var mockList: List<ListModel>

    @Mock
    lateinit var mockListIterator: ListIterator

    lateinit var listPresenter: ListPresenter

    @Before
    fun setUp() {
        listPresenter = ListPresenter(mockListView, mockListIterator)
    }

    @Test
    @Ignore
    fun should_call_updateList_on_getData() {
        `when`(mockListIterator.hitApi(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(Observable.just(mockList))
        listPresenter.getData(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())
    }
}
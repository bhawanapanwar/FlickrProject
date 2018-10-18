package com.bhawana.flickr.FlickrModule.Model

/**
 * **  Created by home on 18-Oct-18.
 */
data class ListModel (
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: String,
        val title: String,
        val ispublic: String,
        val isfriend: String,
        val isfamily: String
)
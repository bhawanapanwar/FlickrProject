package com.bhawana.flickr.FlickrModule

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.bhawana.flickr.FlickrModule.Constant.Constants
import com.bhawana.flickr.FlickrModule.Model.ListModel
import com.bhawana.flickr.MainApplication
import com.bhawana.flickr.NetworkModule.ApiServices
import com.bhawana.flickr.NetworkModule.RetrofitInstance
import com.google.gson.JsonElement
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * **  Created by home on 18-Oct-18.
 */
class ListIterator {

    private lateinit var apiServices: ApiServices
    private lateinit var context:Context

    fun hitApi(text:String,pageNumber: Int): Observable<List<ListModel>> {

        apiServices = RetrofitInstance.retrofitInstance.create(ApiServices::class.java)

        context=MainApplication.applicationContext()

        val call = apiServices.getFlickrImage(Constants.API_KEY,text,
                Constants.FORMAT,Constants.JSON_CALLBACK,pageNumber,Constants.PER_PAGE)

         val observable: PublishSubject<List<ListModel>> = PublishSubject.create<List<ListModel>>()

        call.enqueue(object : Callback<JsonElement> {

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                val result = response.body().toString()
                try {
                    lateinit var mlistArry: MutableList<ListModel>
                    mlistArry = mutableListOf()
                    //Log.e("result1", result)
                    val jsonObject = JSONObject(result)
                    if(jsonObject.has("photos")) {
                        val photos = jsonObject.getJSONObject("photos")
                        val photo = photos.getJSONArray("photo")
                        if (photo.length() > 0) {

                            for (i in 0..(photo.length() - 1)) {
                                val jdata = photo.getJSONObject(i)

                                mlistArry.add(ListModel(jdata.getString("id"), jdata.getString("owner"),
                                        jdata.getString("secret"), jdata.getString("server"), jdata.getString("farm"),
                                        jdata.getString("title"), jdata.getString("ispublic"), jdata.getString("isfriend"),
                                        jdata.getString("isfamily")))
                            }

                        } else {
                            Toast.makeText(context, "No match found!", Toast.LENGTH_SHORT).show()
                        }

                        observable.onNext(mlistArry)
                        observable.onComplete()
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                    observable.onError(e)
                }

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                //Log.e("fail", "")
                observable.onError(t)

            }
        })
        return observable
    }

}
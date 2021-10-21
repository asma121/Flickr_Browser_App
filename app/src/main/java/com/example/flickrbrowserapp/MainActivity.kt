package com.example.flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var button: Button
    lateinit var et:EditText
    lateinit var imageView2: ImageView
    var imageData: Data? = null
    lateinit var images :ArrayList<Photo>
    var tags=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        rv=findViewById(R.id.rv)
        et=findViewById(R.id.et)
        imageView2=findViewById(R.id.imageView2)
        images= arrayListOf()


        button.setOnClickListener {
            tags="${et.text}"
            getImageDetails(tags,"flickr.photos.search","10","76b2587abc33c3289092e0e822964af6","json","1",onResult = {
                imageData = it
                val datumList = imageData!!.photos.photo
                for (datum in datumList!!) {
                    images.add(datum)
                }
                rv.adapter=myAdapter(images,this)
                rv.layoutManager= LinearLayoutManager(this)
            })
        }

    }

    private fun getImageDetails( t:String,m:String,p:String,k:String,f:String,n:String,onResult: (Data?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getImageDetails(t,m,p,k,f,n)?.enqueue(object : Callback<Data?> {
                override fun onResponse(
                    call: Call<Data?>,
                    response: Response<Data?>
                ) {
                    onResult(response.body())
                }
                override fun onFailure(call: Call<Data?>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }
}
//tags,"flickr.photos.search","10","76b2587abc33c3289092e0e822964af6","json","1"

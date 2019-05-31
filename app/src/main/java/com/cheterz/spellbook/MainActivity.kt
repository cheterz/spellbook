package com.cheterz.spellbook

import android.app.FragmentTransaction
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var myAdapter: MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var mySpellsArrayList: ArrayList<Spells>? = null
    private var BASE_URL = "http://85.143.219.56:8090/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerVIew()
        loadData()

    }

    private fun initRecyclerVIew() {
        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
    }

    private fun loadData() {
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)

        myCompositeDisposable?.add(requestInterface.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponce))
    }
    private fun handleResponce(spellsList: List<Spells>){
        mySpellsArrayList = ArrayList(spellsList)
        myAdapter = MyAdapter(mySpellsArrayList!!,this)
        recycler_view.adapter = myAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}

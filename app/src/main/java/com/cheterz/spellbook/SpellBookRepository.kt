package com.cheterz.spellbook

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SpellBookRepository {

    companion object {
        const val BASE_URL = "http://85.143.219.56:8090/v1/"
        const val DICT_URL = "http://85.143.219.56:8090/v1/spellbook/dictionary/"
    }

    fun loadData(): Observable<List<Spells>> {
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)

        return requestInterface.getData()
    }

    fun getDict(): Observable<List<Spells>> {
        val requestInterface = Retrofit.Builder()
                .baseUrl(DICT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetData::class.java)

        return requestInterface.getDict()
    }

}
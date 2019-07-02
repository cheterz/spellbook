package com.cheterz.spellbook

import io.reactivex.Observable
import retrofit2.http.GET

interface GetData {
    @GET("spellbook")
    fun getData(): Observable<List<Spells>>

    @GET("spellbook/dictionary")
    fun getDict(): Observable<List<Spells>>
}
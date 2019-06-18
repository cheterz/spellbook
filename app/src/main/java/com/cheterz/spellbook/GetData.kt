package com.cheterz.spellbook

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface GetData {
    @GET("spellbook")
    fun getData(): Observable<List<Spells>>
}
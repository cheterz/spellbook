package com.cheterz.spellbook

import io.reactivex.Observable
import retrofit2.http.POST

interface GetData {
    @POST("spellbook")
    fun getData(): Observable<List<Spell>>
}
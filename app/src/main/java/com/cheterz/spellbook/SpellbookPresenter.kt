package com.cheterz.spellbook

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SpellbookPresenter(
        private val  mainActivity: SpellsInterfaceView) {
    private var myCompositeDisposable: CompositeDisposable? = null
    private var mySpellsArrayList: ArrayList<Spells>? = null
    private val repository: SpellBookRepository = SpellBookRepository()

    fun viewAttach() {
        repository.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainActivity::handleResponse, Throwable::printStackTrace)
        repository.getDict()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainActivity::handleResponse, Throwable::printStackTrace)
    }
}
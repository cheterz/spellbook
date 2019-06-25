package com.cheterz.spellbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

// TODO: Делаем MVP Model - View - Presenter (ето значит что вью занимается тем что отображает данные и обрабатывает действия от пользователя)
class MainActivity : AppCompatActivity(), MyAdapter.Listener, SpellsInterfaceView {
    override fun handleResponse(spellsList: List<Spells>) {
        myAdapter.update(spellsList)
    }

    private val myAdapter: MyAdapter by lazy { MyAdapter( this) }
    private val presenter: SpellbookPresenter = SpellbookPresenter(this)

    override fun onItemClick(spells: Spells) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerVIew()
        presenter.viewAttach()

    }

    private fun initRecyclerVIew() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = myAdapter

    }

    // TODO: Создать класс репозитория SpellBookRepository.kt - перенести туда в том числе константу
    // TODO: Должна появиться следующая связка (Fragment || Activity) - Presenter - Repository

}


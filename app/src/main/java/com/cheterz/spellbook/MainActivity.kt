package com.cheterz.spellbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Делаем MVP Model - View - Presenter (ето значит что вью занимается тем что отображает данные и обрабатывает действия от пользователя)
class MainActivity : AppCompatActivity(), MyAdapter.Listener {
    private var myAdapter: MyAdapter? = null

    override fun onItemClick(spells: Spells) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // TODO: EXAMPLE: private val repository: SomeRepository = SomeRepository(this) - отдать себя в репозиторий для того чтобы затем отдаь данные обратно во вью. В данном случае this это интерфейс(типа MyAdapter.Listener) который сможет принять .subscribe(THIS_FRAGMENT_POLUCHI::handleResponse, Throwable::printStackTrace)

    // TODO: Это обязанность презентера
    private var myCompositeDisposable: CompositeDisposable? = null
    private var mySpellsArrayList: ArrayList<Spells>? = null
    // END_TODO

    // TODO: Константы в котлине лежат в companion object https://kotlinlang.ru/docs/reference/object-declarations.html - Вспомогательные объекты
    private var BASE_URL = "http://85.143.219.56:8090/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerVIew()
        loadData()
    }

    private fun initRecyclerVIew() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
    }

    // TODO: Создать класс репозитория SpellBookRepository.kt - перенести туда в том числе константу
    // TODO: Должна появиться следующая связка (Fragment || Activity) - Presenter - Repository
    private fun loadData() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetData::class.java)

        myCompositeDisposable?.add(
            requestInterface.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, Throwable::printStackTrace)
        )
    }

    // TODO: станет публичной и будет объявлена в интерфейсе который описан в TODO про презентер
    private fun handleResponse(spellsList: List<Spells>) {
        mySpellsArrayList = ArrayList(spellsList)
        myAdapter = MyAdapter(mySpellsArrayList!!, this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = myAdapter
    }

    // Вот с этим потом перейдём на MOXY
    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}

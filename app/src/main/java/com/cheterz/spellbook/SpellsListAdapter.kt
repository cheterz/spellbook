package com.cheterz.spellbook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.spell_card_item.view.*

class SpellsListAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<SpellsListAdapter.ViewHolder>() {
    private val spellsList: MutableList<Spells> = mutableListOf()

    interface Listener {
        fun onItemClick(spells: Spells)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(spellsList[position], listener)
    }

    override fun getItemCount(): Int = spellsList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spell_card_item, parent, false)
        return ViewHolder(view)
    }

    fun update(spellsList: List<Spells>) {
        this.spellsList.clear()
        this.spellsList.addAll(spellsList)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var schoolName = ""
        var sourceName = ""
        var levelName = ""

        fun bind(spellsList: Spells, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClick(spellsList)
            }
            when (spellsList.schoolId){
                1 -> schoolName = "Воплощение"
                2 -> schoolName = "Вызов"
                3 -> schoolName = "Иллюзия"
                4 -> schoolName = "Некромантия"
                5 -> schoolName = "Ограждение"
                6 -> schoolName = "Очарование"
                7 -> schoolName = "Преобразование"
                8 -> schoolName = "Прорицание"
                else -> "Нет Школы"


            }
            when (spellsList.sourceId){
                1 -> sourceName = "Player's handbook"
                2 -> sourceName = "Princes of the Apocalypse, Xanathar's Guide to Everything"
                3 -> sourceName = "Sword Coast Adventurer Guide"
                5 -> sourceName = "Guildmasters' guide to Ravnica"
                6 -> sourceName = "Homebrew"
                7 -> sourceName = "Xanathar’s Guide to Everything"
            }
            when(spellsList.levelId){
                1 -> levelName = "Заговор"
                2 -> levelName = "Уровень: 1"
                3 -> levelName = "Уровень: 2"
                4 -> levelName = "Уровень: 3"
                5 -> levelName = "Уровень: 4"
                6 -> levelName = "Уровень: 5"
                7 -> levelName = "Уровень: 6"
                8 -> levelName = "Уровень: 7"
                9 -> levelName = "Уровень: 8"
                10 -> levelName = "Уровень: 9"
            }
            itemView.tv_name_of_spell.text = spellsList.title
            itemView.tv_school_of_spell.text = "Школа: " + schoolName
            itemView.tv_level_of_spell.text = levelName
            itemView.tv_source_of_spell.text = "Источник: " + sourceName
        }
    }
}
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
            itemView.tv_name_of_spell.text = spellsList.title
            itemView.tv_school_of_spell.text = "Школа: " + schoolName
            itemView.tv_level_of_spell.text = "Уровень: " + spellsList.levelId
            itemView.tv_source_of_spell.text = "Источник: " + sourceName
        }
    }
}
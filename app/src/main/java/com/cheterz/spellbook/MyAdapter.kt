package com.cheterz.spellbook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.spell_card_item.view.*

class MyAdapter(
        private val spellsList: ArrayList<Spells>,
        private val listener: Listener
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(spellsList: Spells, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClick(spellsList)
            }
            itemView.tv_name_of_spell.text = spellsList.title
            itemView.tv_school_of_spell.text = spellsList.schoolId
            itemView.tv_level_of_spell.text = spellsList.levelId
        }
    }
}
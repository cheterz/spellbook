package com.cheterz.spellbook

data class Spells(val id: Int, val name: String, val level: Int, val school: String, val distance: Double,
                  val components: String, val duration: Double, val classOfSpell: String,
                  val sourceOfSpell: String, val descr: String)
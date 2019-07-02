package com.cheterz.spellbook

import com.google.gson.annotations.SerializedName

data class Spells(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("level_id") val levelId: String,
    @SerializedName("school_id") val schoolId: String,
    @SerializedName("using_time") val usingTime: String,
    @SerializedName("distance") val distance: String,
    @SerializedName("components") val components: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("classes") val classes: List<String>,
    @SerializedName("source_id") val sourceId: String,
    @SerializedName("description") val description: String
)

package com.example.cafexml.data

data class Cafe(
    val name: String,
    val desc: String,
    val image: Int,
    val mapsUrl: String,
    val rating: String,
    val isFeatured: Boolean = false
)
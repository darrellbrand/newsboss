package com.djf.newsboss.util

data class APILatestResult(
    val status: String = "",
    val totalResults: Int = -1,
    val results: List<APILatestResultItem>
)

data class APILatestResultItem(
    val title: String = "",
    val link: String = "",
    val description: String = "",
    val content: String = "",
    val image_url: String = "",
    val video_url: String = "",
    val source_id: String ="",
    val source_icon: String = "",
    val pubDate: String =""
)

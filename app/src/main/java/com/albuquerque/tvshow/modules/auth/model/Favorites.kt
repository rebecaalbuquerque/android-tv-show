package com.albuquerque.tvshow.modules.auth.model

import com.albuquerque.tvshow.modules.shows.model.Show
import com.google.gson.annotations.SerializedName

class Favorites {

    var page: Int = 0

    @SerializedName("results")
    var list: List<Show> = listOf()

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

}
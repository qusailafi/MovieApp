package com.example.movie.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

//Utils class contains on the keys used or any function will call from deffrient place
object Utils {
    var POPULARITY: String = "popularity.desc"
    var REVENUE: String = "revenue.desc"
    var TOPRATED: String = "vote_average.desc"
    var FILLTER_KEY: String = "sort_by"
    var API_KEY = "api_key"
    var MOVIEW_SELECTED: String = "item"
    var PAGE_KEY: String = "page"
    var MOVIE_IMAGE_SIZE:String="w300"
    var CONNECTION_TIME_OUT:Long=90

    fun goToActivity(ctx: Context, to: Class<*>?, bundle: Bundle) {
        val i = Intent(ctx, to)
        i.putExtras(bundle)
        ctx.startActivity(i)
     }
}
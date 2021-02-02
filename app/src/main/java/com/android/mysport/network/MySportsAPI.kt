package com.android.mysport.network

import com.android.mysport.model.SportEvents
import com.android.mysport.model.Teams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The API interface to define the endpoints
 */
interface MySportsAPI {
    @GET("eventspastleague.php?")
    fun getPastEventForLeague(@Query("id") id: String?): Call<SportEvents?>?

    @GET("searchteams.php?")
    fun getTeamList(@Query("t") team: String?): Call<Teams?>?

    @GET("eventsnextleague.php?")
    fun getFutureEvents(@Query("id") id: String?): Call<SportEvents?>?
}
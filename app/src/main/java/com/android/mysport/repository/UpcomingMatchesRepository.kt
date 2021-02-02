package com.android.mysport.repository

import androidx.lifecycle.MutableLiveData
import com.android.mysport.model.ResponseObject
import com.android.mysport.model.SportEvents
import com.android.mysport.network.MySportsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The upcoming matches repository which is responsible for getting the data from the API
 */
class UpcomingMatchesRepository(mySportsAPI: MySportsAPI) {

    private val mySportsAPI: MySportsAPI
    val sportsEventsData: MutableLiveData<ResponseObject>

    init {
        sportsEventsData = MutableLiveData()
        this.mySportsAPI = mySportsAPI
    }

    fun getEventsForLeagueCode(leagueCode: String) {
        val call = mySportsAPI.getFutureEvents(leagueCode)!!
        call.enqueue(object : Callback<SportEvents?> {
            override fun onResponse(call: Call<SportEvents?>, response: Response<SportEvents?>) {
                if (response.isSuccessful) {
                    sportsEventsData.value = ResponseObject(response.body())
                }
            }

            override fun onFailure(call: Call<SportEvents?>, t: Throwable) {
                sportsEventsData.value = ResponseObject(t)
            }
        })
    }

}
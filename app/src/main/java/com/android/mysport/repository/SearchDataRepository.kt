package com.android.mysport.repository

import androidx.lifecycle.MutableLiveData
import com.android.mysport.model.ResponseObject
import com.android.mysport.model.Teams
import com.android.mysport.network.MySportsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The search repository which is responsible for getting the data from the API
 */
class SearchDataRepository(mySportsAPI: MySportsAPI) {

    private val mySportsAPI: MySportsAPI
    val teamsData: MutableLiveData<ResponseObject>

    init {
        teamsData = MutableLiveData()
        this.mySportsAPI = mySportsAPI
    }

    fun getTeams(query: String) {
        val call = mySportsAPI.getTeamList(query)!!
        call.enqueue(object : Callback<Teams?> {
            override fun onResponse(call: Call<Teams?>, response: Response<Teams?>) {
                if (response.isSuccessful) {
                    teamsData.value = ResponseObject(response.body())
                }
            }

            override fun onFailure(call: Call<Teams?>, t: Throwable) {
                teamsData.value = ResponseObject(t)
            }
        })
    }


}
package com.android.mysport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mysport.model.ResponseObject
import com.android.mysport.network.MySportsAPI
import com.android.mysport.repository.SportsDataRepository

/**
 * The viewmodel for previous score updates
 */
class SportsDataViewModel : ViewModel() {
    private val data = MutableLiveData<ResponseObject>()

    fun init(mySportsAPI: MySportsAPI, leagueCode: String) {
        val sportsDataRepository = SportsDataRepository(mySportsAPI)
        sportsDataRepository.getEventsForLeagueCode(leagueCode)
        sportsDataRepository.sportsEventsData.observeForever { responseObject: ResponseObject -> data.setValue(responseObject) }
    }

    fun getData(): LiveData<ResponseObject> {
        return data
    }
}
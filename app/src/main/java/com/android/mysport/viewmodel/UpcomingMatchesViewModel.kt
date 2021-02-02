package com.android.mysport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mysport.model.ResponseObject
import com.android.mysport.network.MySportsAPI
import com.android.mysport.repository.UpcomingMatchesRepository

/**
 * The viewmodel for upcoming matches data
 */
class UpcomingMatchesViewModel : ViewModel() {

    private val data = MutableLiveData<ResponseObject>()

    fun init(mySportsAPI: MySportsAPI, leagueCode: String) {
        val upcomingMatchesRepository = UpcomingMatchesRepository(mySportsAPI)
        upcomingMatchesRepository.getEventsForLeagueCode(leagueCode)
        upcomingMatchesRepository.sportsEventsData.observeForever { responseObject: ResponseObject -> data.setValue(responseObject) }
    }

    fun getData(): LiveData<ResponseObject> {
        return data
    }
}
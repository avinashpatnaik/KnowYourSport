package com.android.mysport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mysport.model.ResponseObject
import com.android.mysport.network.MySportsAPI
import com.android.mysport.repository.SearchDataRepository

/**
 * The viewmodel for search data
 */
class SearchDataViewModel : ViewModel() {

    private val data = MutableLiveData<ResponseObject>()

    fun init(mySportsAPI: MySportsAPI, query: String) {
        val searchDataRepository = SearchDataRepository(mySportsAPI)
        searchDataRepository.getTeams(query)
        searchDataRepository.teamsData.observeForever { responseObject: ResponseObject -> data.setValue(responseObject) }
    }

    val teamsData: LiveData<ResponseObject>
        get() = data
}
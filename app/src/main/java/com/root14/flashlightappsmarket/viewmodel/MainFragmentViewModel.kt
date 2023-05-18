package com.root14.flashlightappsmarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.root14.flashlightappsmarket.network.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ilkay on 18,May, 2023
 */
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    init {
        println("init oldum")
        viewModelScope.launch {
            //sampleReq()
        }
    }

    suspend fun sampleReq() {
        val response = mainRepository.colorlights()
        println(response)
    }


    //make request and save it
}
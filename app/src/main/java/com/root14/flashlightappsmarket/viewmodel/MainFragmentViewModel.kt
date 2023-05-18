package com.root14.flashlightappsmarket.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.root14.flashlightappsmarket.network.Resource
import com.root14.flashlightappsmarket.network.models.response.AppResponse
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


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        Log.d(this::class.java.name, "has initialized")
        fetchFlashLight()
        fetchColorLight()
        fetchSosAlert()
    }

    private val _flashLightRes = MutableLiveData<Resource<List<AppResponse>>>()
    val flashLightRes: LiveData<Resource<List<AppResponse>>> get() = _flashLightRes
    private fun fetchFlashLight() = viewModelScope.launch {
        _isLoading.postValue(true)
        mainRepository.flashlights().let {
            if (it.isSuccessful) {
                _flashLightRes.postValue(Resource.success(it.body()))
                Log.d("fetchFlashLight", "request success.")
            } else {
                _flashLightRes.postValue(Resource.error(it.errorBody().toString(), null))
                Log.e("fetchFlashLight", "error: ${it.errorBody().toString()} ")
            }
            _isLoading.postValue(false)
        }
    }

    private val _colorLight = MutableLiveData<Resource<List<AppResponse>>>()
    val colorLight: LiveData<Resource<List<AppResponse>>> get() = _colorLight
    private fun fetchColorLight() = viewModelScope.launch {
        _isLoading.postValue(true)
        mainRepository.flashlights().let {
            if (it.isSuccessful) {
                _colorLight.postValue(Resource.success(it.body()))
                Log.d("fetchColorLight", "request success.")
            } else {
                _colorLight.postValue(Resource.error(it.errorBody().toString(), null))
                Log.e("fetchColorLight", "error: ${it.errorBody().toString()} ")
            }
            _isLoading.postValue(false)
        }
    }

    private val _sosAlerts = MutableLiveData<Resource<List<AppResponse>>>()
    val sosAlerts: LiveData<Resource<List<AppResponse>>> get() = _sosAlerts
    private fun fetchSosAlert() = viewModelScope.launch {
        _isLoading.postValue(true)
        mainRepository.flashlights().let {
            if (it.isSuccessful) {
                _sosAlerts.postValue(Resource.success(it.body()))
                Log.d("fetchSosAlert", "request success.")
            } else {
                _sosAlerts.postValue(Resource.error(it.errorBody().toString(), null))
                Log.e("fetchSosAlert", "error: ${it.errorBody().toString()} ")
            }
            _isLoading.postValue(false)
        }
    }





}
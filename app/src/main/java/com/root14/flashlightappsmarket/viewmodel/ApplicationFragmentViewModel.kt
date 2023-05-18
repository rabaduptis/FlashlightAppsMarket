package com.root14.flashlightappsmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.root14.flashlightappsmarket.data.dao.ColoredLightDao
import com.root14.flashlightappsmarket.data.dao.FlashlightDao
import com.root14.flashlightappsmarket.data.dao.SOSAlertDao
import com.root14.flashlightappsmarket.data.entity.ColoredLight
import com.root14.flashlightappsmarket.data.entity.Flashlight
import com.root14.flashlightappsmarket.data.entity.SOSAlert
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
class ApplicationFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val flashlightDao: FlashlightDao,
    private val coloredLightDao: ColoredLightDao,
    private val sosAlertDao: SOSAlertDao
) : ViewModel() {


    init {
        viewModelScope.launch {
            println("douglas bak init oldum!")
        }
    }

    fun fetchDB() {
        viewModelScope.launch {
            getAllFlashlights()
            getAllColoredLights()
            getAllSosAlerts()
        }
    }


    /**
     * @return all flashLights from db
     */
    private val _getAllFlashlightsRes = MutableLiveData<List<Flashlight>>()
    val getAllFlashlightsRes: LiveData<List<Flashlight>> get() = _getAllFlashlightsRes
    private suspend fun getAllFlashlights() = viewModelScope.launch {
        _getAllFlashlightsRes.postValue(flashlightDao.getAllFlashlights())
    }

    /**
     * @return all colorLights from db
     */
    private val _colorLightRes = MutableLiveData<List<ColoredLight>>()
    val getAllColorLightRes: LiveData<List<ColoredLight>> get() = _colorLightRes
    private suspend fun getAllColoredLights() = viewModelScope.launch {
        _colorLightRes.postValue(coloredLightDao.getAllColoredLights())
    }

    /**
     * @return all SosAlerts from db
     */
    private val _sosAlerts = MutableLiveData<List<SOSAlert>>()
    val getAllSosAlerts: LiveData<List<SOSAlert>> get() = _sosAlerts
    private suspend fun getAllSosAlerts() = viewModelScope.launch {
        _sosAlerts.postValue(sosAlertDao.getAllSOSAlerts())
    }


}
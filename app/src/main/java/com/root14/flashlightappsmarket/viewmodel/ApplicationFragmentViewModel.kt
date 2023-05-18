package com.root14.flashlightappsmarket.viewmodel

import android.graphics.drawable.Drawable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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
    private val sosAlertDao: SOSAlertDao,
    private val glide: RequestManager
) : ViewModel() {


    init {
        viewModelScope.launch {
            println("douglas bak init oldum!")
        }
    }

    private suspend fun sortRaitingValue() {

    }

    private suspend fun raitingCount() {

    }


    //add search (via name and/or packageName) support for the list.
    // and/or based on "or" preferred
    //----------------------------------------------------------------------------------------------

    //region searchByName
    private val _flashLightSearchByName = MutableLiveData<List<Flashlight>>()
    val flashLightSearchByName: LiveData<List<Flashlight>> get() = _flashLightSearchByName
    suspend fun flashLightSearchByName(name: String) = viewModelScope.launch {
        _getAllFlashlightsRes.postValue(flashlightDao.searchByName(name))
    }


    private val _colorLightSearchByName = MutableLiveData<List<ColoredLight>>()
    val colorLightSearchByName: LiveData<List<ColoredLight>> get() = _colorLightSearchByName
    suspend fun colorLightSearchByName(name: String) = viewModelScope.launch {
        _colorLightSearchByName.postValue(coloredLightDao.searchByName(name))
    }


    private val _sosAlertsSearchByName = MutableLiveData<List<SOSAlert>>()
    val sosAlertsSearchByName: LiveData<List<SOSAlert>> get() = _sosAlertsSearchByName
    suspend fun sosAlertsSearchByName(name: String) = viewModelScope.launch {
        _colorLightSearchByName.postValue(coloredLightDao.searchByName(name))
    }
    //endregion

    //----------------------------------------------------------------------------------------------

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

    fun loadImage(imageUrl: String, imageState: MutableState<Drawable?>) {
        glide.load(imageUrl).into(object : CustomTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable, transition: Transition<in Drawable?>?
            ) {
                imageState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }


}
package com.root14.flashlightappsmarket.viewmodel

import android.util.Log
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
class MainFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val flashlightDao: FlashlightDao,
    private val coloredLightDao: ColoredLightDao,
    private val sosAlertDao: SOSAlertDao
) : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        Log.d(this::class.java.name, "has initialized")
        viewModelScope.launch {
            if (flashlightDao.getAllFlashlights().isEmpty()) {
                fetchFlashLight()
            }

            if (coloredLightDao.getAllColoredLights().isEmpty()) {
                fetchColorLight()
            }

            if (sosAlertDao.getAllSOSAlerts().isEmpty()) {
                fetchSosAlert()
            }
        }
    }

    private val _flashLightRes = MutableLiveData<Resource<List<AppResponse>>>()
    val flashLightRes: LiveData<Resource<List<AppResponse>>> get() = _flashLightRes
    private fun fetchFlashLight() = viewModelScope.launch {
        _isLoading.postValue(true)
        mainRepository.flashlights().let { it ->
            if (it.isSuccessful) {
                _flashLightRes.postValue(Resource.success(it.body()))
                Log.d("fetchFlashLight", "request success.")

                val appResponseList: List<AppResponse>? = it.body()
                val flashlightList: List<Flashlight> = appResponseList?.map {
                    Flashlight(
                        name = it.name.toString(),
                        packageName = it.packageName.toString(),
                        iconUrl = it.iconUrl.toString(),
                        price = it.price.toString(),
                        ratingValue = it.ratingValue,
                        ratingCount = it.ratingCount,
                        downloads = it.downloads.toString(),
                        version = it.version.toString(),
                        category = it.category.toString(),
                        developerName = it.developerName.toString(),
                        developerEmail = it.developerEmail.toString(),
                        developerAddress = it.developerAddress.toString()
                    )
                } ?: emptyList()

                flashlightDao.insertAllFlashlights(flashlightList)
                _flashLightRes.postValue(Resource.success(appResponseList))
                Log.d("fetchFlashLight", "db insert success.")

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
        mainRepository.flashlights().let { it ->
            if (it.isSuccessful) {
                _colorLight.postValue(Resource.success(it.body()))
                Log.d("fetchColorLight", "request success.")

                val appResponseList: List<AppResponse>? = it.body()
                val coloredLight: List<ColoredLight> = appResponseList?.map {
                    ColoredLight(
                        name = it.name.toString(),
                        packageName = it.packageName.toString(),
                        iconUrl = it.iconUrl.toString(),
                        price = it.price.toString(),
                        ratingValue = it.ratingValue,
                        ratingCount = it.ratingCount,
                        downloads = it.downloads.toString(),
                        version = it.version.toString(),
                        category = it.category.toString(),
                        developerName = it.developerName.toString(),
                        developerEmail = it.developerEmail.toString(),
                        developerAddress = it.developerAddress.toString()
                    )
                } ?: emptyList()

                coloredLightDao.insertAllColoredLight(coloredLight)
                _colorLight.postValue(Resource.success(appResponseList))
                Log.d("fetchFlashLight", "db insert success.")

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

                val appResponseList: List<AppResponse>? = it.body()
                val sosAlert: List<SOSAlert> = appResponseList?.map {
                    SOSAlert(
                        name = it.name.toString(),
                        packageName = it.packageName.toString(),
                        iconUrl = it.iconUrl.toString(),
                        price = it.price.toString(),
                        ratingValue = it.ratingValue,
                        ratingCount = it.ratingCount,
                        downloads = it.downloads.toString(),
                        version = it.version.toString(),
                        category = it.category.toString(),
                        developerName = it.developerName.toString(),
                        developerEmail = it.developerEmail.toString(),
                        developerAddress = it.developerAddress.toString()
                    )
                } ?: emptyList()

                sosAlertDao.insertAllSOSAlert(sosAlert)
                _sosAlerts.postValue(Resource.success(appResponseList))
                Log.d("fetchFlashLight", "db insert success.")

            } else {
                _sosAlerts.postValue(Resource.error(it.errorBody().toString(), null))
                Log.e("fetchSosAlert", "error: ${it.errorBody().toString()} ")
            }
            _isLoading.postValue(false)
        }
    }

    private fun insertFlashLight() = viewModelScope.launch {
    }

}
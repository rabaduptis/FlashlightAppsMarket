package com.root14.flashlightappsmarket.viewmodel

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestManager
import com.root14.flashlightappsmarket.data.SortType
import com.root14.flashlightappsmarket.data.dao.ColoredLightDao
import com.root14.flashlightappsmarket.data.dao.FlashlightDao
import com.root14.flashlightappsmarket.data.dao.SOSAlertDao
import com.root14.flashlightappsmarket.data.entity.ColoredLight
import com.root14.flashlightappsmarket.data.entity.Flashlight
import com.root14.flashlightappsmarket.data.entity.SOSAlert
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

    fun checkApp(packageName: String, context: Context) {
        val packageManager = context.packageManager

        try {
            val packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            val launchIntent = packageManager.getLaunchIntentForPackage(packageName)

            if (launchIntent != null) {
                context.startActivity(launchIntent)
            } else {
                openPlayStore(packageName, context)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            openPlayStore(packageName, context)
        } catch (e: ActivityNotFoundException) {
            openPlayStore(packageName, context)
        }
    }

    //Checks if the application is installed on the device and opens the relevant page in the playstore
    private fun openPlayStore(packageName: String, context: Context) {
        try {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data = Uri.parse("market://details?id=$packageName")
            playStoreIntent.setPackage("com.android.vending")
            context.startActivity(playStoreIntent)
        } catch (e: ActivityNotFoundException) {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            context.startActivity(playStoreIntent)
        }
    }


    //case: add search (via name and/or packageName) support for the list.
    // and/or based on, "or" preferred
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

    //fetches api
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
    suspend fun getAllFlashlights(sortType: SortType = SortType.DEFAULT) = viewModelScope.launch {
        _getAllFlashlightsRes.postValue(flashlightDao.getAllFlashlights())
        when (sortType) {
            SortType.DEFAULT -> {

            }

            SortType.VALUE -> {
                val flashlightList = _getAllFlashlightsRes.value
                val sortedList = flashlightList?.sortedBy { it.ratingValue }
                _getAllFlashlightsRes.postValue(sortedList!!)
            }

            SortType.COUNT -> {
                val flashlightList = _getAllFlashlightsRes.value
                val sortedList = flashlightList?.sortedBy { it.ratingCount }
                _getAllFlashlightsRes.postValue(sortedList!!)
            }
        }
    }

    /**
     * @return all colorLights from db
     */
    private val _colorLightRes = MutableLiveData<List<ColoredLight>>()
    val getAllColorLightRes: LiveData<List<ColoredLight>> get() = _colorLightRes
    suspend fun getAllColoredLights(sortType: SortType = SortType.DEFAULT) = viewModelScope.launch {
        _colorLightRes.postValue(coloredLightDao.getAllColoredLights())
        when (sortType) {
            SortType.DEFAULT -> {

            }

            SortType.VALUE -> {
                val coloredLightList = _colorLightRes.value
                val sortedList = coloredLightList?.sortedBy { it.ratingValue }
                _colorLightRes.postValue(sortedList!!)
            }

            SortType.COUNT -> {
                val coloredLightList = _colorLightRes.value
                val sortedList = coloredLightList?.sortedBy { it.ratingCount }
                _colorLightRes.postValue(sortedList!!)
            }
        }
    }

    /**
     * @return all SosAlerts from db
     */
    private val _sosAlerts = MutableLiveData<List<SOSAlert>>()
    val getAllSosAlertsRes: LiveData<List<SOSAlert>> get() = _sosAlerts
    suspend fun getAllSosAlerts(sortType: SortType = SortType.DEFAULT) =
        viewModelScope.launch {
            _sosAlerts.postValue(sosAlertDao.getAllSOSAlerts())
            when (sortType) {
                SortType.DEFAULT -> {

                }

                SortType.VALUE -> {
                    val sosAlertList = _sosAlerts.value
                    val sortedList = sosAlertList?.sortedBy { it.ratingValue }
                    _sosAlerts.postValue(sortedList!!)
                }

                SortType.COUNT -> {
                    val sosAlertList = _sosAlerts.value
                    val sortedList = sosAlertList?.sortedBy { it.ratingCount }
                    _sosAlerts.postValue(sortedList!!)
                }
            }

        }
}
package com.root14.flashlightappsmarket.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.root14.flashlightappsmarket.data.dao.ColoredLightDao
import com.root14.flashlightappsmarket.data.dao.FlashlightDao
import com.root14.flashlightappsmarket.data.dao.SOSAlertDao
import com.root14.flashlightappsmarket.data.entity.ColoredLight
import com.root14.flashlightappsmarket.data.entity.Flashlight
import com.root14.flashlightappsmarket.data.entity.SOSAlert

@Database(
    entities = [Flashlight::class, ColoredLight::class, SOSAlert::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flashlightDao(): FlashlightDao
    abstract fun coloredLightDao(): ColoredLightDao
    abstract fun sosAlertDao(): SOSAlertDao
}
package com.root14.flashlightappsmarket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.root14.flashlightappsmarket.data.entity.SOSAlert

@Dao
interface SOSAlertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSOSAlert(sosAlert: List<SOSAlert>)

    @Query("SELECT * FROM sos_alerts")
    suspend fun getAllSOSAlerts(): List<SOSAlert>
}
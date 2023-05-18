package com.root14.flashlightappsmarket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.root14.flashlightappsmarket.data.entity.Flashlight

@Dao
interface FlashlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFlashlights(flashlights: List<Flashlight>)

    @Query("SELECT * FROM flashlights")
    suspend fun getAllFlashlights(): List<Flashlight>
}
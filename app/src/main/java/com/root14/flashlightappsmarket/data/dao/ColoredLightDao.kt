package com.root14.flashlightappsmarket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.root14.flashlightappsmarket.data.entity.ColoredLight

@Dao
interface ColoredLightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllColoredLight(coloredLight: List<ColoredLight>)

    @Query("SELECT * FROM colored_lights")
    suspend fun getAllColoredLights(): List<ColoredLight>
}
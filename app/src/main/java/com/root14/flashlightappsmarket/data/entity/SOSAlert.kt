package com.root14.flashlightappsmarket.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sos_alerts")
data class SOSAlert(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "package_name") val packageName: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "icon_url") val iconUrl: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "rating_value") val ratingValue: Double?,
    @ColumnInfo(name = "rating_count") val ratingCount: Int?,
    @ColumnInfo(name = "downloads") val downloads: String?,
    @ColumnInfo(name = "version") val version: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "developer_name") val developerName: String?,
    @ColumnInfo(name = "developer_email") val developerEmail: String?,
    @ColumnInfo(name = "developer_address") val developerAddress: String?
)
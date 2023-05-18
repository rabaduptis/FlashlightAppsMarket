package com.root14.flashlightappsmarket.data

import com.root14.flashlightappsmarket.data.entity.ColoredLight
import com.root14.flashlightappsmarket.data.entity.Flashlight
import com.root14.flashlightappsmarket.data.entity.SOSAlert
import com.root14.flashlightappsmarket.model.AppItem

/**
 * Created by ilkay on 18,May, 2023
 */
class DataUtils {
    companion object {

        private fun List<Flashlight>.flashlightToAppItemList(): List<AppItem> {
            return this.map { flashlight ->
                AppItem(
                    icon = flashlight.iconUrl.toString(),
                    name = flashlight.name.toString(),
                    ratingValue = flashlight.ratingValue.toString(),
                    ratingCount = flashlight.ratingCount.toString(),
                    downloadCount = flashlight.downloads.toString()
                )
            }
        }

        private fun List<ColoredLight>.coloredLight2AppItemList(): List<AppItem> {
            return this.map { flashlight ->
                AppItem(
                    icon = flashlight.iconUrl.toString(),
                    name = flashlight.name.toString(),
                    ratingValue = flashlight.ratingValue.toString(),
                    ratingCount = flashlight.ratingCount.toString(),
                    downloadCount = flashlight.downloads.toString()
                )
            }
        }

        private fun List<SOSAlert>.sosAlert2AppItemList(): List<AppItem> {
            return this.map { flashlight ->
                AppItem(
                    icon = flashlight.iconUrl.toString(),
                    name = flashlight.name.toString(),
                    ratingValue = flashlight.ratingValue.toString(),
                    ratingCount = flashlight.ratingCount.toString(),
                    downloadCount = flashlight.downloads.toString()
                )
            }
        }
    }
}

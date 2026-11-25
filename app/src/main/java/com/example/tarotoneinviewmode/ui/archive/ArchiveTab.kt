package com.example.tarotoneinviewmode.ui.archive

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Suit

enum class ArchiveTab(
    @StringRes val titleRes: Int,
    val arcanaType: ArcanaType?,
    //@DrawableRes val iconRes: Int,
    val suit: Suit?,
    val element: Element?,
    @DrawableRes val backgroundRes: Int? // 🚀 Null indica que usará o bg_default do Scaffold
) {
    MAJOR(
        titleRes = R.string.tab_major,
        //iconRes = R.drawable.ic_major,
        arcanaType = ArcanaType.MAJOR,
        suit = null,
        element = null,
        backgroundRes = null 
    ),
    WANDS(
        titleRes = R.string.tab_wands,
        //iconRes = R.drawable.ic_wands,
        arcanaType = ArcanaType.MINOR,
        suit = Suit.WANDS,
        element = Element.FIRE,
        backgroundRes = R.drawable.bg_fire
    ),
    CUPS(
        titleRes = R.string.tab_cups,
        //iconRes = R.drawable.ic_cups,
        arcanaType = ArcanaType.MINOR,
        suit = Suit.CUPS,
        element = Element.WATER,
        backgroundRes = R.drawable.bg_water
    ),
    SWORDS(
        titleRes = R.string.tab_swords,
        //iconRes = R.drawable.ic_swords,
        arcanaType = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        element = Element.AIR,
        backgroundRes = R.drawable.bg_air
    ),
    PENTACLES(
        titleRes = R.string.tab_pentacles,
        //iconRes = R.drawable.ic_pentacles,
        arcanaType = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        element = Element.EARTH,
        backgroundRes = R.drawable.bg_earth
    ),
    FAVORITES(
        titleRes = R.string.filter_favorites,
        //iconRes = R.drawable.ic_menu_home, // Placeholder
        arcanaType = null,
        suit = null,
        element = null,
        backgroundRes = null
    )
    ;

    companion object{
        val tabEntries = entries
    }
}

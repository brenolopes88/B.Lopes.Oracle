package com.example.tarotoneinviewmode.domain.model

import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Planet
import com.example.tarotoneinviewmode.domain.enums.ZodiacSign

data class Correspondences(
    val element: Element,
    val planet: Planet?,
    val zodiac: ZodiacSign?,
    val numerology: Int?
)

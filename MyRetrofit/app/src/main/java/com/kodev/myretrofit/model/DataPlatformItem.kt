package com.kodev.myretrofit.model

data class DataPlatformItem(
    val platform: Platform,
    val released_at: String,
    val requirements_en: RequirementsEn? = null,
    val requirements_ru: Any? = null
)
package com.example.tarotoneinviewmode.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.preferences.OraculoPreferences
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val preferences: OraculoPreferences
) : ViewModel() {

    val onboardingCompleted: StateFlow<Boolean?> = preferences.onboardingCompleted
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val privacyNoticeSeen: StateFlow<Boolean?> = preferences.privacyNoticeSeen
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun setPrivacyNoticeSeen(seen: Boolean) {
        viewModelScope.launch {
            preferences.setPrivacyNoticeSeen(seen)
        }
    }

    fun dismissPrivacyNotice(context: android.content.Context) {
        viewModelScope.launch {
            preferences.setPrivacyNoticeSeen(true)
            val enabled = preferences.analyticsEnabled.first()
            if (enabled) {
                AnalyticsHelper.setCollectionEnabled(context, true)
            }
        }
    }
}

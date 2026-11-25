package com.example.tarotoneinviewmode.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.preferences.OraculoPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferences: OraculoPreferences
) : ViewModel() {
    
    fun completeOnboarding(onComplete: () -> Unit) {
        viewModelScope.launch {
            preferences.setOnboardingCompleted(true)
            onComplete()
        }
    }
}

package com.example.tarotoneinviewmode

import android.app.Application
import com.example.tarotoneinviewmode.data.preferences.OraculoPreferences
import com.example.tarotoneinviewmode.notifications.NotificationHelper
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import com.example.tarotoneinviewmode.util.RemoteConfigHelper
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@HiltAndroidApp
class OraculoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.criarCanal(this)
        AnalyticsHelper.initialize(this)
        
        val prefs = OraculoPreferences(this)
        val (noticeSeen, analyticsEnabled) = runBlocking {
            Pair(prefs.privacyNoticeSeen.first(), prefs.analyticsEnabled.first())
        }

        val shouldEnable = noticeSeen && analyticsEnabled
        AnalyticsHelper.setCollectionEnabled(this, shouldEnable)

        RemoteConfigHelper.initialize()
    }
}

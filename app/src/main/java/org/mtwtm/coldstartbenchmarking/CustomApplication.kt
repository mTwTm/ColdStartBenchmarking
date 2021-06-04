package org.mtwtm.coldstartbenchmarking

import android.app.Application
import android.os.SystemClock
import org.mtwtm.coldstartbenchmarking.metadata.ColdStartStats

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ColdStartStats.applicationOnCreateMs = SystemClock.uptimeMillis()
        ColdStartStats.applicationLoadedMs = LOAD_TIME
    }

    companion object {
        val LOAD_TIME = SystemClock.uptimeMillis()
    }
}

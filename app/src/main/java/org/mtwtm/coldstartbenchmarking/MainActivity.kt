package org.mtwtm.coldstartbenchmarking

import android.os.Build
import android.os.Bundle
import android.os.Process
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import org.mtwtm.coldstartbenchmarking.metadata.ColdStartStats

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ColdStartStats.mainOnCreateMs = SystemClock.uptimeMillis()
        ColdStartStats.mainLoadedMs = LOADED_MS
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            ColdStartStats.processCreatedMs = Process.getStartUptimeMillis()
        }
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        ColdStartStats.mainOnStartMs = SystemClock.uptimeMillis()
    }

    override fun onResume() {
        super.onResume()
        ColdStartStats.mainOnResumeMs = SystemClock.uptimeMillis()
    }

    companion object {
        val LOADED_MS = SystemClock.uptimeMillis()
    }
}

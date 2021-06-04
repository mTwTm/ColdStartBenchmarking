package org.mtwtm.coldstartbenchmarking

import android.os.*
import android.widget.TextView
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
        Handler(Looper.getMainLooper()).postDelayed({
            val content = "processCreatedMs: ${ColdStartStats.processCreatedMs}\n" +
                    "applicationLoadedMs: ${ColdStartStats.applicationLoadedMs}\n" +
                    "emptyProviderLoadedMs: ${ColdStartStats.emptyProviderOnCreateMs}\n" +
                    "emptyProviderOnCreateMs: ${ColdStartStats.emptyProviderOnCreateMs}\n" +
                    "applicationOnCreateMs: ${ColdStartStats.applicationOnCreateMs}\n" +
                    "mainLoadedMs: ${ColdStartStats.mainLoadedMs}\n" +
                    "mainOnCreateMs: ${ColdStartStats.mainOnCreateMs}\n" +
                    "mainOnStartMs: ${ColdStartStats.mainOnStartMs}\n" +
                    "mainOnResumeMs: ${ColdStartStats.mainOnResumeMs}\n" +
                    "firstDrawMs: ${ColdStartStats.firstDrawMs}\n"
            findViewById<TextView>(R.id.text).text = content
        }, 1000L)
    }

    companion object {
        val LOADED_MS = SystemClock.uptimeMillis()
    }
}

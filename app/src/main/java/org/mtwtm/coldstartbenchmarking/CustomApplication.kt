package org.mtwtm.coldstartbenchmarking

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import org.mtwtm.coldstartbenchmarking.coldstart.NextDrawListener.Companion.onNextDraw
import org.mtwtm.coldstartbenchmarking.coldstart.WindowDelegateCallback.Companion.onDecorViewReady
import org.mtwtm.coldstartbenchmarking.metadata.ColdStartStats

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        var firstDraw = false
        val handler = Handler(Looper.getMainLooper())

        registerActivityLifecycleCallbacks(
            object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(
                    activity: Activity,
                    savedInstanceState: Bundle?
                ) {
                    if (firstDraw) return
                    val window = activity.window
                    window.onDecorViewReady {
                        window.decorView.onNextDraw {
                            if (firstDraw) return@onNextDraw
                            firstDraw = true
                            handler.postAtFrontOfQueue {
                                ColdStartStats.firstDrawMs = SystemClock.uptimeMillis()
                            }
                        }
                    }
                }

                override fun onActivityStarted(activity: Activity) {
                    // Do nothing.
                }

                override fun onActivityResumed(activity: Activity) {
                    // Do nothing.
                }

                override fun onActivityPaused(activity: Activity) {
                    // Do nothing.
                }

                override fun onActivityStopped(activity: Activity) {
                    // Do nothing.
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    // Do nothing.
                }

                override fun onActivityDestroyed(activity: Activity) {
                    // Do nothing.
                }
            })
        ColdStartStats.applicationOnCreateMs = SystemClock.uptimeMillis()
        ColdStartStats.applicationLoadedMs = LOAD_TIME
    }

    companion object {
        val LOAD_TIME = SystemClock.uptimeMillis()
    }
}

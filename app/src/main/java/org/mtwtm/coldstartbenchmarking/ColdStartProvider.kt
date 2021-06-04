package org.mtwtm.coldstartbenchmarking

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.SystemClock
import org.mtwtm.coldstartbenchmarking.metadata.ColdStartStats

class ColdStartProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        ColdStartStats.emptyProviderOnCreateMs = SystemClock.uptimeMillis()
        ColdStartStats.emptyProviderLoadedMs = LOADED_TIME
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    companion object {
        val LOADED_TIME = SystemClock.uptimeMillis()
    }
}

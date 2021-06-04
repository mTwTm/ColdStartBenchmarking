package org.mtwtm.coldstartbenchmarking.metadata

object ColdStartStats {
    var processCreatedMs: Long? = null
    var applicationLoadedMs: Long? = null
    var emptyProviderLoadedMs: Long? = null
    var emptyProviderOnCreateMs: Long? = null
    var applicationOnCreateMs: Long? = null
    var mainLoadedMs: Long? = null
    var mainOnCreateMs: Long? = null
    var mainOnStartMs: Long? = null
    var mainOnResumeMs: Long? = null
    var firstDrawMs: Long? = null
}

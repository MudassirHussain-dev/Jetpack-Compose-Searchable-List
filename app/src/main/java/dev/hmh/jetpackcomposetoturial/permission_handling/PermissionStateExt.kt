package dev.hmh.jetpackcomposetoturial.permission_handling

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
fun PermissionState.isPermissionDenied(): Boolean {
    return !shouldShowRationale && !hasPermission
}
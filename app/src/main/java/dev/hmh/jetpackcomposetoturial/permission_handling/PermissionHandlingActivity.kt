package dev.hmh.jetpackcomposetoturial.permission_handling

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dev.hmh.jetpackcomposetoturial.ui.theme.JetpackComposeToturialTheme

@ExperimentalPermissionsApi
class PermissionHandlingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeToturialTheme {
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )
                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifecycleOwner, effect = {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_RESUME) {
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                })
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    permissionState.permissions.forEach { perm ->
                        when (perm.permission) {
                            Manifest.permission.CAMERA -> {
                                when {
                                    perm.hasPermission -> {
                                        Text(text = "Camera permission is accepted")
                                    }
                                    perm.shouldShowRationale -> {
                                        Text(text = "Camera permission is needed to access the camera")
                                    }
                                    /* !perm.hasPermission && !perm.shouldShowRationale -> {
                                     }*/
                                    perm.isPermissionDenied() -> {
                                        Text(text = "Camera permission was permanently denied. you can enable it in the app settings")
                                    }
                                }
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    perm.hasPermission -> {
                                        Text(text = "Record audio permission is accepted")
                                    }
                                    perm.shouldShowRationale -> {
                                        Text(text = "Record audio permission is needed to access the camera")
                                    }
                                    /* !perm.hasPermission && !perm.shouldShowRationale -> {
                                     }*/
                                    perm.isPermissionDenied() -> {
                                        Text(text = "Record audio permission was permanently denied. you can enable it in the app settings")
                                    }
                                }
                            }
                        }

                    }

                }
            }


        }
    }
}
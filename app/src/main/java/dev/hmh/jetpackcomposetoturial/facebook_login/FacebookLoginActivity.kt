@file:Suppress("UNUSED_EXPRESSION")

package dev.hmh.jetpackcomposetoturial.facebook_login

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import dev.hmh.jetpackcomposetoturial.ui.theme.JetpackComposeToturialTheme
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class FacebookLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: FacebookLoginViewModel by viewModels()
        setContent {
            JetpackComposeToturialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    printHashKey(LocalContext.current)

                    val profileViewState by viewModel.profileViewState.observeAsState(
                        ProfileViewState()
                    )
                    SimpleView(profileViewState, login, logout)
                }
            }
        }
    }

    private val login = {
        LoginManager.getInstance().logIn(
            this@FacebookLoginActivity, CallbackManager.Factory.create(),
            listOf("email")
        )
    }

    private val logout = {
        LoginManager.getInstance().logOut()
    }
}

@Composable
fun SimpleView(profileViewState: ProfileViewState, login: () -> Unit, logout: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FacebookLoginButton(
            profile = profileViewState.profile,
            login = { login },
            logout = { logout },
        )

        Spacer(modifier = Modifier.height(15.dp))
        WrappedLoginButton()
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = profileViewState?.profile?.name ?: "logout")
    }
}

fun printHashKey(context: Context) {
    try {
        val info: PackageInfo = context.packageManager
            .getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            val hashKey: String = String(Base64.encode(md.digest(), 0))
            Log.d("hashkey", "Hash Key: $hashKey")
        }
    } catch (e: NoSuchAlgorithmException) {
        Log.e("Error", "${e.localizedMessage}")
    } catch (e: Exception) {
        Log.e("Exception", "${e.localizedMessage}")
    }
}
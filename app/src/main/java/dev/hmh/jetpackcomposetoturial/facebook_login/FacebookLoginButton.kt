package dev.hmh.jetpackcomposetoturial.facebook_login

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.facebook.Profile

@Composable
fun FacebookLoginButton(
    profile: Profile?,
    login: () -> Unit,
    logout: () -> Unit
) {
    val buttonText = if (profile == null) "Continue with Facebook" else "Log out"
    val onClick = if (profile == null) login else logout
    Button(onClick = { onClick }) {
        Text(text = buttonText)
    }
}

@Preview
@Composable
fun FacebookLoginButtonPreview() {

}
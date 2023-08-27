package jp.one_system_group.diary_sample_android.ui.auth

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import jp.one_system_group.diary_sample_android.auth.TopLevel
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthEmail
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthLogin
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthPassword
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthTitle
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme

@ExperimentalMaterialApi
@Composable
fun AuthScreen(
    loginProcess: (String, String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    TopLevel() {
        AuthTitle()
        AuthEmail(
            value = email,
            onValueChange = { newValue -> email = newValue },
        )
        AuthPassword(
            value = password,
            onValueChange = { newValue -> password = newValue },
        )
        AuthLogin(
            onClick = { loginProcess(email, password) },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun Preview() {
    // Jetpack Composeで表示する用のメソッド
    DiarySampleAndroidTheme {
        AuthScreen { _, _ -> }
    }
}
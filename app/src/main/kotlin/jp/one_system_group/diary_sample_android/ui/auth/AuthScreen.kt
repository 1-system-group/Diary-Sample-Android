package jp.one_system_group.diary_sample_android.ui.auth

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import jp.one_system_group.diary_sample_android.auth.TopLevel
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthEmail
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthLogin
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthPassword
import jp.one_system_group.diary_sample_android.ui.auth.component.AuthTitle
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme

@ExperimentalMaterialApi
@Composable
fun AuthScreen(
    navController: NavHostController,
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
            onClick = {
                navController.navigate("main")
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun Preview() {
    // Jetpack Composeで表示する用のメソッド
    val navController = rememberNavController()
    DiarySampleAndroidTheme {
        AuthScreen(
            navController
        )
    }
}
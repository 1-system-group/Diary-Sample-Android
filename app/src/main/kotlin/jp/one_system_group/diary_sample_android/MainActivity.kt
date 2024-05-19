package jp.one_system_group.diary_sample_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.one_system_group.diary_sample_android.ui.home.HomeScreen
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme
import jp.one_system_group.diary_sample_android.viewmodel.AuthViewModel
import jp.one_system_group.diary_sample_android.viewmodel.DiaryListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val diaryListViewModel: DiaryListViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiarySampleAndroidTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(
                        navController,
                        // 認証処理
                        login(navController),
                        // 日記一覧画面の表示に必要なデータ
                        diaryListViewModel.diaryList
                    )
                }
            }
        }
    }

    @Composable
    private fun login(navController: NavHostController): (String, String) -> Unit =
        { email, password ->
            // 認証処理を呼び出す
            authViewModel.login(email, password)
            CoroutineScope(Dispatchers.Main).launch {
                // 認証結果を待つ
                val result = authViewModel.resultFlow.firstOrNull { it != null }
                if (result != null) {
                    // 認証が完了したら、日記一覧画面へ遷移する
                    navController.navigate("main")
                }
            }
        }
}
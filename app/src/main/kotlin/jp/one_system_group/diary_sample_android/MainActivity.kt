package jp.one_system_group.diary_sample_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.ui.home.HomeScreen
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme
import jp.one_system_group.diary_sample_android.viewmodel.DiaryListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: DiaryListViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawScreen(viewModel.diaryList)
    }

    @ExperimentalMaterialApi
    private fun drawScreen(list: Flow<PagingData<DiaryRow>>) {
        setContent {
            DiarySampleAndroidTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(navController, list)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DefaultPreview() {
    // Jetpack Composeで表示する用のメソッド
    DiarySampleAndroidTheme {
        // 残念ながらページ読み込み付きのリストプレビューは表示されない
        val diaryList = flowOf(
            PagingData.from((15 downTo 1).map {
                DiaryRow(it, "投稿${it}", "2021/01/01")
            })
        )
        val navController = rememberNavController()
        HomeScreen(navController, diaryList)
    }
}
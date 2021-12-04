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
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.ui.home.HomeScreen
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme
import jp.one_system_group.diary_sample_android.viewmodel.DiaryListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: DiaryListViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDiaryList().observe(this, {
            // APIから取得した結果を画面に反映する
            drawScreen(it)
        })
        // 初期表示
        drawScreen(emptyList())
    }

    @ExperimentalMaterialApi
    private fun drawScreen(list: List<DiaryRow>) {
        setContent {
            DiarySampleAndroidTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(list)
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
        val diaryList = (15 downTo 1).map {
            DiaryRow(it, "投稿${it}", "2021/01/01")
        }
        HomeScreen(diaryList)
    }
}
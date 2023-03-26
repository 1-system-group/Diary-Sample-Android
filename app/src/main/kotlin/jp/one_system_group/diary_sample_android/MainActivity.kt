package jp.one_system_group.diary_sample_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.ui.home.HomeScreen
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme
import jp.one_system_group.diary_sample_android.viewmodel.DiaryListViewModel
import kotlinx.coroutines.flow.Flow

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
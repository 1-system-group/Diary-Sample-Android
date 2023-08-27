package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.ui.home.component.DrawerShape
import jp.one_system_group.diary_sample_android.ui.home.component.ModalDrawerContent
import jp.one_system_group.diary_sample_android.ui.home.component.TopBar
import jp.one_system_group.diary_sample_android.ui.theme.DiarySampleAndroidTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    diaryList: Flow<PagingData<DiaryRow>>,
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scaffoldState, scope) },
        content = { DiaryList(navController, diaryList) },
        drawerContent = { ModalDrawerContent(scaffoldState, scope) },
        drawerShape = DrawerShape()
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MainScreenPreview() {
    val diaryList = flowOf(
        PagingData.from((15 downTo 1).map {
            DiaryRow(it, "投稿${it}", "2021/01/01")
        })
    )
    // Jetpack Composeで表示する用のメソッド
    val navController = rememberNavController()
    DiarySampleAndroidTheme {
        MainScreen(
            navController,
            diaryList
        )
    }
}
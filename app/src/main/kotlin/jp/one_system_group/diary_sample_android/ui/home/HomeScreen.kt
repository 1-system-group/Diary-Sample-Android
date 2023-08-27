package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.PagingData
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.ui.auth.AuthScreen
import kotlinx.coroutines.flow.Flow

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    diaryList: Flow<PagingData<DiaryRow>>,
) {

    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        // 認証画面
        composable(route = "auth") {
            AuthScreen(navController)
        }
        // 日記一覧画面
        composable(route = "main") {
            MainScreen(navController, diaryList)
        }
        // 日記参照画面
        composable(route = "reference/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: throw Exception()
            DiaryScreen(navController, id)
        }
    }
}




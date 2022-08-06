package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.one_system_group.diary_sample_android.model.DiaryRow

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    diaryList: List<DiaryRow>
) {

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

        composable(route = "main") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "日記")
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Filled.Menu, contentDescription = null)
                            }
                        },
                    )
                },
                content = {
                    DiaryList(navController, diaryList)
                }
            )
        }
        composable(route = "reference/{id}",
            arguments = listOf(
                navArgument("id") {type = NavType.IntType}
            )
        ) { backStackEntry->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            if (id == -1) {
                Exception()
            }
            DiaryScreen(navController, id)
        }
    }
}




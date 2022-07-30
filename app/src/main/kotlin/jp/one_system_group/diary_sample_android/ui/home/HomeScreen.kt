package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.viewmodel.DiaryViewModel

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
        composable(route = "reference") {
            DiaryScreen(navController)
        }
    }
}




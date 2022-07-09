package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.viewmodel.ReferenceMessageViewModel
import kotlinx.coroutines.launch

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
            val viewModel = hiltViewModel<ReferenceMessageViewModel>()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text(viewModel.referenceMessage.title) },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { scaffoldState.drawerState.open() }
                                }
                            ) {
                                Icon(Icons.Filled.Close, contentDescription = null)
                            }
                        }
                    )
                },
                content = {
                    Text(text = viewModel.referenceMessage.content)
                }
            )
        }
    }
}




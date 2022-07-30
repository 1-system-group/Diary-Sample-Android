package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import jp.one_system_group.diary_sample_android.viewmodel.DiaryViewModel

@Composable
fun DiaryScreen(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<DiaryViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val navigationIcon: (@Composable () -> Unit)? =
        if (navBackStackEntry?.destination?.route != "main") {
            {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }

    Scaffold(

        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(viewModel.diary.title) },
                navigationIcon = navigationIcon,
            )
        },
        content = {
            Text(text = viewModel.diary.content)
        }
    )
}

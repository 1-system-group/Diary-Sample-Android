package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.PagingData
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.ui.menu.DrawerScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    diaryList: Flow<PagingData<DiaryRow>>
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

        composable(route = "main") {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "日記")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = null)
                            }
                        },
                    )
                },
                content = {
                    DiaryList(navController, diaryList)
                },
                drawerContent = {
                    ModalDrawer(
                        drawerContent = {
                        },
                        content = {
                            DrawerScreen(
                                scaffoldState = scaffoldState,
                                scope = scope,
                                navController = navController
                            )
                        }
                    )
                },
                drawerShape = object : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {
                        return Outline.Rectangle(
                            Rect(
                                left = 0f,
                                top = 0f,
                                right = 750f,
                                bottom = 2000f
                            )
                        )
                    }
                }
            )

            composable(route = "reference") {
                DiaryScreen(navController)
            }
        }
    }
}




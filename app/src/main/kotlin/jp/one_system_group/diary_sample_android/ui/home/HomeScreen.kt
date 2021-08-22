package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import jp.one_system_group.diary_sample_android.model.DiaryRow

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    diaryList: List<DiaryRow>
) {
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
            DiaryList(diaryList)
        }
    )
}



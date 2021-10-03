package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.flow.Flow

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    diaryList: Flow<PagingData<DiaryRow>>
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



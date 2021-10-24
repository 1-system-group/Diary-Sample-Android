import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jp.one_system_group.diary_sample.ui.home.DiaryList
import jp.one_system_group.diary_sample_android.model.Diary

@ExperimentalMaterialApi
@Preview
@Composable
fun HomeScreen() {
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
            val diaries = (15 downTo 1).map {
                Diary("投稿${it}", "2021/01/01")
            }
            DiaryList(diaries)
        }
    )
}



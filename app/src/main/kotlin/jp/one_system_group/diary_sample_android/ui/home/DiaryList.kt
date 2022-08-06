package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.flow.Flow

@ExperimentalMaterialApi
@Composable
fun DiaryList(
    navController: NavHostController,
    diaryList: Flow<PagingData<DiaryRow>>
) {
    val lazyDiaryItems = diaryList.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyDiaryItems) { item ->
            if (item != null) {
                ListItem(
                    modifier = Modifier.clickable(onClick = {
                        // リストアイテムクリック時の処理
                        navController.navigate("reference/" + item.id)
                    }),
                    text = { Text(item.title) },
                    secondaryText = { Text(item.postDate) })
                Divider()
            }
        }
        lazyDiaryItems.apply {
            when {
                // 初回読み込み
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                // 追加読み込み
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                // 初回読み込み時エラー
                loadState.refresh is LoadState.Error -> {
                    val e = lazyDiaryItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                // 追加読み込み時エラー
                loadState.append is LoadState.Error -> {
                    val e = lazyDiaryItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}
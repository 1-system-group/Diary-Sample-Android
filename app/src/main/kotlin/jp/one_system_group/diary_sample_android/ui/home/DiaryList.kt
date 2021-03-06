package jp.one_system_group.diary_sample_android.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.one_system_group.diary_sample_android.model.DiaryRow

@ExperimentalMaterialApi
@Composable
fun DiaryList(diaryList: List<DiaryRow>) {
    LazyColumn {
        itemsIndexed(diaryList) { _, item ->
            ListItem(
                modifier = Modifier.clickable(onClick = {}),
                text = { Text(item.title) },
                secondaryText = { Text(item.postDate) })
            Divider()
        }
    }
}
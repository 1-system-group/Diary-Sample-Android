package jp.one_system_group.diary_sample_android.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DrawerItem(item: DrawerListItem, selected: Boolean, onItemClick: (DrawerListItem) -> Unit) {
    val background =
        if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else Color.Transparent
    val textColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = { onItemClick(item) })
            .background(background)
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        Icon(item.icon, contentDescription = item.route, tint = textColor)
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            text = item.title,
            fontSize = 18.sp,
            style = MaterialTheme.typography.subtitle1,
            color = textColor,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerItemPreview() {
    Column() {
    DrawerItem(item = DrawerListItem.Favorite, selected = false, onItemClick = {})
    DrawerItem(item = DrawerListItem.Bookmark, selected = false, onItemClick = {})
    DrawerItem(item = DrawerListItem.Setting, selected = true, onItemClick = {})
    }
}
package jp.one_system_group.diary_sample_android.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.one_system_group.diary_sample_android.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class DrawerListItem(val route: String, val title: String, val icon: ImageVector) {
    object Favorite : DrawerListItem("Favorite", "お気に入り", Icons.Rounded.Favorite)
    object Bookmark : DrawerListItem("Bookmark", "ブックマーク", Icons.Rounded.Bookmark)
    object Setting : DrawerListItem("Setting", "設定", Icons.Rounded.Settings)
}

@Composable
fun DrawerScreen(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
) {
    val items = listOf(
        DrawerListItem.Favorite,
        DrawerListItem.Bookmark,
        DrawerListItem.Setting
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.design_default_color_on_primary))

    ) {
        Spacer(
            modifier = Modifier
                .height(50.dp)
                .width(1000.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(50.dp)
                .height(50.dp)
                .offset(x = 30.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
        )
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
                .padding(16.dp), content = {
                Text("test@example.jp", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("20XX/01/01", color = Color.DarkGray)
            }, verticalArrangement = Arrangement.Bottom
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
        )
        items.forEach { item ->
            DrawerItem(item = item, selected = false, onItemClick = {
                // Close drawer
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    DrawerScreen(scope = scope, scaffoldState = scaffoldState)
}
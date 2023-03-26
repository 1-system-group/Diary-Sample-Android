package jp.one_system_group.diary_sample_android.ui.home.component

import androidx.compose.material.ModalDrawer
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import jp.one_system_group.diary_sample_android.ui.menu.DrawerScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun ModalDrawerContent(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    ModalDrawer(
        drawerContent = {
        },
        content = {
            DrawerScreen(
                scaffoldState = scaffoldState,
                scope = scope,
            )
        }
    )
}

class DrawerShape() : Shape {
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
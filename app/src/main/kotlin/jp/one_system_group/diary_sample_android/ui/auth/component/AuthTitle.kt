package jp.one_system_group.diary_sample_android.ui.auth.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.google.relay.compose.BoxScopeInstance.boxAlign
import jp.one_system_group.diary_sample_android.auth.Title

@Composable
fun AuthTitle() {
    Title(
        modifier = Modifier.boxAlign(
            alignment = Alignment.TopCenter,
            offset = DpOffset(
                x = 0.dp,
                y = 120.0.dp
            )
        )
    )
}
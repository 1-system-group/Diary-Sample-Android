package jp.one_system_group.diary_sample_android.ui.auth.component

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.relayDropShadow

@Composable
fun AuthLogin(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(
                alpha = 255,
                red = 251,
                green = 251,
                blue = 253
            )
        ),
        modifier = Modifier
            .boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.dp,
                    y = 350.dp
                )
            )
            .relayDropShadow(
                color = Color(
                    alpha = 63,
                    red = 0,
                    green = 0,
                    blue = 0
                ),
                borderRadius = 1.0.dp,
                blur = 28.0.dp,
                offsetX = 5.0.dp,
                offsetY = 6.0.dp,
                spread = 0.dp
            ),
    ) {
        Text(
            text = "LOGIN",
            style = TextStyle(
                color = Color(
                    alpha = 255,
                    red = 38,
                    green = 31,
                    blue = 31
                ),
                letterSpacing = 1.25.sp,
                fontWeight = FontWeight(500.0.toInt()),
                textAlign = TextAlign.Left,
            )
        )
    }
}
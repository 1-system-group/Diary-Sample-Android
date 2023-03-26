package jp.one_system_group.diary_sample_android.ui.auth.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.relayDropShadow
import jp.one_system_group.diary_sample_android.auth.Password

@Composable
fun AuthPassword(value: String, onValueChange: (String) -> Unit) {
    Password(
        modifier = Modifier
            .boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.dp,
                    y = 250.0.dp
                )
            )
            .relayDropShadow(
                color = Color(
                    alpha = 63,
                    red = 0,
                    green = 0,
                    blue = 0
                )
            )
    ) {
        AuthTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = "パスワード",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
        )
    }
}
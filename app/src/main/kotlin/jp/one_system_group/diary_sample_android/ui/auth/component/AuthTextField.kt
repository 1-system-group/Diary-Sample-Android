package jp.one_system_group.diary_sample_android.ui.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visualTransformation: VisualTransformation,
    keyboardType: KeyboardType
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done,
        ),
        textStyle = TextStyle(
            fontSize = 16.0.sp,
            color = Color(
                alpha = 255,
                red = 38,
                green = 31,
                blue = 31
            ),
            letterSpacing = 0.15.sp,
            textAlign = TextAlign.Left,
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(
                alpha = 20,
                red = 251,
                green = 251,
                blue = 253
            )
        ),
        maxLines = 1,
        modifier = Modifier
            .height(50.0.dp)
            .fillMaxWidth(1.0f)
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

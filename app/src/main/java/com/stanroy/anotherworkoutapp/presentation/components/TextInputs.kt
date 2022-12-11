package com.stanroy.anotherworkoutapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stanroy.anotherworkoutapp.presentation.common.spacing_24
import com.stanroy.anotherworkoutapp.presentation.common.spacing_48
import com.stanroy.anotherworkoutapp.presentation.ui.theme.AnotherWorkoutAppTypography

@Composable
fun SimpleTextInput(
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing_48)
            .heightIn(min = 45.dp)
            .background(color = Color.White, RoundedCornerShape(30.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing_24),
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            decorationBox = {

                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = AnotherWorkoutAppTypography.spanishGrayUiL
                    )
                }
                it()
            }
        )
    }
}
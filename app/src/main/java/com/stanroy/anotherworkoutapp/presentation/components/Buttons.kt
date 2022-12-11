package com.stanroy.anotherworkoutapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stanroy.anotherworkoutapp.presentation.common.spacing_22
import com.stanroy.anotherworkoutapp.presentation.common.spacing_4

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onClick, shape = RoundedCornerShape(30.dp)) {
            Text(
                modifier = Modifier.padding(horizontal = spacing_22, vertical = spacing_4),
                text = label
            )
        }
    }
}
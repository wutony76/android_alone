package com.happyfat.alone.theme


import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
@Composable
fun TestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}
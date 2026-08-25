package com.panov.sevastyan.ndpa.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    NdpaTheme {
        Surface(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
@Preview
private fun AppPreview() {
    App()
}

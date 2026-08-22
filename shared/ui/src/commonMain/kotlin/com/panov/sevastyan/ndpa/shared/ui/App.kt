package com.panov.sevastyan.ndpa.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme

@Composable
@Preview
fun App() {
    NdpaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}

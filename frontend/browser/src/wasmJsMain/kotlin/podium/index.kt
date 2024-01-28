@file:OptIn(ExperimentalComposeUiApi::class)

package podium

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

fun main() {
    CanvasBasedWindow("Podium", "root") {
        App()
    }
}
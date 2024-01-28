@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)

package podium

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        CanvasBasedWindow("Podium", "root") {
            App()
        }
    }
}
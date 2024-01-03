import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(width = 400.dp, height = 600.dp)

    Window(
        onCloseRequest = ::exitApplication,
        title = "SlidingPuzzle",
        state = state
    ) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}
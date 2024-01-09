package puzzle

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object CameraScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Text("Camera", style = MaterialTheme.typography.h2)
        Camera(
            onPhotoCapture = { bytearray ->
                navigator.replace(PuzzleScreen(bytearray))
            },
            onClose = {
                navigator.pop()
            }
        )
    }
}

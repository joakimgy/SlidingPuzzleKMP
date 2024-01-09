package puzzle

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object CameraScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.rememberNavigatorScreenModel { PuzzleViewModel() }


        Text("Camera", style = MaterialTheme.typography.h2)
        Camera(
            onPhotoCapture = { image ->
                screenModel.setGalleryImage(image)
                navigator.replace(PuzzleScreen)
            },
            onClose = {
                navigator.pop()
            }
        )
    }
}

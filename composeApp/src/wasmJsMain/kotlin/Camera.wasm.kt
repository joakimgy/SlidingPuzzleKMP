import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun Camera(
    onPhotoCapture: (photo: ByteArray) -> Unit,
    onClose: () -> Unit
) {
    Text(text = "Camera WASM not supported")
    Button(onClick = onClose) {
        Text("Go back")
    }
}


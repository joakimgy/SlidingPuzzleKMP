import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.Button

@Composable
actual fun Camera(
    onPhotoCapture: (photo: ByteArray) -> Unit,
    onClose: () -> Unit
) {
    Text(text = "Camera iOS not supported")
    Button(onClick = onClose) {
        Text("Go back")
    }
}

@Composable
actual fun CapturedImage(
    imageData: ByteArray,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier
) {
    // TODO: Implement
}
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun Camera(onPhotoCapture: (photo: ByteArray) -> Unit, onClose: () -> Unit)

@Composable
expect fun CapturedImage(
    imageData: ByteArray,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier
)
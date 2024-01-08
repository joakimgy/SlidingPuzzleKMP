import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.sarxos.webcam.Webcam


@Composable
actual fun Camera(
    onPhotoCapture: (photo: ByteArray) -> Unit,
    onClose: () -> Unit
) {
    val cameras = remember { mutableStateOf(Webcam.getWebcams()) }
    val camera = remember { mutableStateOf<Webcam?>(null) }

    BoxWithConstraints() {
        if (camera.value == null) {
            if (cameras.value.isEmpty()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "No cameras found on your system. Please attach a camera to continue",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Text("Cameraa value is not empty")
            }
        } else {
            Text("Camera exists")
        }
    }
}

@Composable
actual fun CapturedImage(
    imageData: ByteArray,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier
) {
    // TODO: Impelement
}
package puzzle

import androidx.compose.runtime.Composable

@Composable
expect fun Camera(onPhotoCapture: (photo: ByteArray) -> Unit, onClose: () -> Unit)


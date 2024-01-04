import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.ByteArrayOutputStream
import java.io.File


@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun Camera(
    onPhotoCapture: (photo: ByteArray) -> Unit,
    onClose: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    CameraContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        onPhotoCapture = onPhotoCapture
    )
}

@Composable
private fun CameraContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    onPhotoCapture: (photo: ByteArray) -> Unit
) {
    if (hasPermission) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val cameraController = remember { LifecycleCameraController(context) }

        Text(text = "Camera Android (with permission)")
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Take photo") },
                    onClick = {
                        val mainExecture = ContextCompat.getMainExecutor((context))
                        cameraController.takePicture(
                            mainExecture,
                            object : ImageCapture.OnImageCapturedCallback() {
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    super.onCaptureSuccess(image)
                                    val stream = ByteArrayOutputStream()
                                    //convertBitmapToFile(image.toBitmap())
                                    image.toBitmap().compress(Bitmap.CompressFormat.WEBP_LOSSY, 10, stream)
                                    val bytearray = stream.toByteArray()
                                    Log.d("myspecialcodeword", "Bytearray size : ${bytearray.size}")
                                    onPhotoCapture(bytearray)
                                }
                            }
                        )
                    }
                )
            }
        ) { paddingValues ->
            AndroidView(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                factory = {
                    PreviewView(it).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        setBackgroundColor(Color.BLACK)
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { previewView ->
                        previewView.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                })
        }
    } else {
        Button(onClick = onRequestPermission) {
            Text(text = "Camera Android (missing permission)")
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
    val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    bitmap.prepareToDraw()
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

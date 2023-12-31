import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import kotlinx.coroutines.runBlocking
import puzzle.PuzzleViewModel
import unsplash.UnsplashRepository

@Composable
fun TopBar() {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = navigator.rememberNavigatorScreenModel { PuzzleViewModel(UnsplashRepository()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .padding(bottom = 1.dp).background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            enabled = navigator.canPop,
            onClick = { navigator.pop() },
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text("Go back")
        }
        Text(
            "Puzzles & Quiz",
            textAlign = TextAlign.Center,
        )
        ChangePhotoButton(onSelectPhoto = { image ->
            if (image != null) {
                screenModel.setGalleryImage(image)
            } else {
                screenModel.resetToDefaultImage()
            }
        })
    }
}

@Composable
fun ChangePhotoButton(onSelectPhoto: (image: ByteArray?) -> Unit) {
    var showFilePicker by remember { mutableStateOf(false) }
    val fileType = listOf("jpg", "png")

    Row(modifier = Modifier.padding(end = 10.dp)) {
        Button(
            onClick = { showFilePicker = true }
        ) {
            Text("Change photo")
        }
        Box(modifier = Modifier.height(0.dp).width(0.dp)) {
            FilePicker(show = showFilePicker, fileExtensions = fileType) { file ->
                showFilePicker = false
                runBlocking {
                    val image = file?.getFileByteArray()
                    onSelectPhoto(image)
                }
            }
        }
    }
}
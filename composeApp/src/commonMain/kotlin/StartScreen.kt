import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import okio.FileSystem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import puzzle.PuzzleScreen
import puzzle.PuzzleViewModel

object StartScreen : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Text("Start Screen", style = MaterialTheme.typography.h2)
        Button(onClick = { navigator.push(PuzzleScreen()) }) {
            Text("Start puzzle")
        }
        Button(onClick = { navigator.push(QuizScreen) }) {
            Text("Go to Quiz")
        }
    }
}

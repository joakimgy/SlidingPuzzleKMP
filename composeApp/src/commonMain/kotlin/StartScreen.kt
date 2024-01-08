import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object StartScreen : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Text("Start Screen", style = MaterialTheme.typography.h2)
        Button(onClick = { navigator.push(PuzzleScreen) }) {
            Text("Start puzzle")
        }
    }
}

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App() {

    MaterialTheme {
        Navigator(StartScreen) {
            Scaffold(
                topBar = { TopBar() },
                content = {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        CurrentScreen()
                    }
                },
                bottomBar = { BottomBar() }
            )
        }
    }
}

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class PuzzleScreen(
    val image: ByteArray?
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var points by remember { mutableStateOf(0) }
        var board by remember { mutableStateOf(generatePuzzle(solved = true)) }

        fun onPuzzleCompleted() {
            board = generatePuzzle()
            points++
        }

        Text("Puzzle", style = MaterialTheme.typography.h2)
        Column(
            Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(if (!board.isSolved()) "Good luck" else "Well done!")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Point: $points")
                Spacer(Modifier.width(20.dp))
                if (board.isSolved()) {
                    Button(onClick = {
                        onPuzzleCompleted()
                    }) {
                        Text("New puzzle")
                    }
                } else {
                    Button(onClick = { board = generatePuzzle() }) {
                        Text("Reset puzzle")
                    }
                }
                Spacer(Modifier.width(20.dp))
                Button(onClick = { navigator.push(CameraScreen) }) {
                    Text("Take photo")
                }
            }

            Spacer(Modifier.height(16.dp))
            PuzzleKeyboardListener(
                board = board,
                onSquareMove = { board = board.moveSquare(it) },
                onEnterPress = {
                    if (board.isSolved()) {
                        onPuzzleCompleted()
                    }
                }) {
                PuzzleView(board = board, image = image) {
                    board = board.moveSquare(it)
                }
            }
        }
    }
}
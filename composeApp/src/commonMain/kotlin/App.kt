import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        var points by remember { mutableStateOf(0) }
        var board by remember { mutableStateOf(generatePuzzle(solved = true)) }

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
                        board = generatePuzzle()
                        points++
                    }) {
                        Text("New puzzle")
                    }
                } else {
                    Button(onClick = { board = generatePuzzle() }) {
                        Text("Reset puzzle")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            PuzzleView(board) {
                board = board.moveSquare(it)
            }
        }
    }
}

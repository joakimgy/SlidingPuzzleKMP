import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp

@Composable
fun App() {

    MaterialTheme {
        var points by remember { mutableStateOf(0) }
        var board by remember { mutableStateOf(generatePuzzle(solved = true)) }

        fun onPuzzleCompleted() {
            board = generatePuzzle()
            points++
        }

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
                PuzzleView(board) {
                    board = board.moveSquare(it)
                }
            }
        }
    }
}

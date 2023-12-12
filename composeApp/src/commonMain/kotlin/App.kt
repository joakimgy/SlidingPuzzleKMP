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
        var board by remember { mutableStateOf(generatePuzzle(solved = true)) }
        println("Solved: ${board.isSolved()}")

        Column(
            Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = { board = generatePuzzle() }) {
                Text("Reset puzzles")
            }
            Text(if (!board.isSolved()) "Good luck" else "Well done!")
            Spacer(Modifier.height(16.dp))
            Puzzle(board = board) {
                board = it
            }
        }
    }
}

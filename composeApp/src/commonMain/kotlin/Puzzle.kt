import androidx.compose.runtime.Composable

@Composable
fun Puzzle(board: List<Int>, onBoardChanged: (List<Int>) -> Unit) {
    PuzzleView(board) {
        println("Pressed square at index $it")
        onBoardChanged(board.moveSquare(it))
    }
}

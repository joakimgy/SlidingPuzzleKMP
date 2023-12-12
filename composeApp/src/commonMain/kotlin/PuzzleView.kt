import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PuzzleView(board: List<Int>) {
    Box(modifier = Modifier.widthIn(max = 600.dp).border(2.dp, Color.Black)) {
        LazyVerticalGrid(columns = GridCells.Fixed(PUZZLE_SIZE)) {
            items(board) { item ->
                val isEmptySquare = item == 0
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .then(if (!isEmptySquare) Modifier.background(Color(101, 222, 77)) else Modifier),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (item != 0) "$item" else "",
                        style = TextStyle(fontSize = 24.sp),
                    )
                }

            }
        }
    }
}
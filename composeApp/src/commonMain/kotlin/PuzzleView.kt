import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PuzzleView(board: List<Int>, onClick: (squareIndex: Int) -> Unit) {
    Box(modifier = Modifier.widthIn(max = 600.dp).border(2.dp, Color.Black)) {
        LazyVerticalGrid(columns = GridCells.Fixed(board.getSize())) {
            items(board.withIndex().toList()) { item ->
                val isEmptySquare = item.value == 0
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .then(if (!isEmptySquare) Modifier.background(Color(101, 222, 77)) else Modifier)
                        .clickable { onClick(item.index) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = if (!isEmptySquare) "${item.value}" else "",
                        style = TextStyle(fontSize = 24.sp),
                    )
                }
            }
        }
    }
}

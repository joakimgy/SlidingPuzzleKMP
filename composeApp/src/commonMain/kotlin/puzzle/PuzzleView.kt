package puzzle

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PuzzleView(board: List<Int>, state: PuzzleViewModel.State, onClick: (squareIndex: Int) -> Unit) {
    Box(modifier = Modifier.widthIn(max = 600.dp).border(2.dp, Color.Black)) {
        LazyVerticalGrid(columns = GridCells.Fixed(board.getSize())) {
            items(board.withIndex().toList()) { item ->
                val isEmptySquare = item.value == 0
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clickable { onClick(item.index) },
                    contentAlignment = Alignment.Center,
                ) {
                    val puzzleSize = board.getSize()
                    if (!isEmptySquare) {
                        Box(modifier = Modifier.fillMaxSize().clipToBounds()) {
                            val contentDescription =
                                "Square ${item.value} at row ${item.index / puzzleSize}, column ${item.index % puzzleSize}"
                            val contentScale = ContentScale.Crop
                            val modifier = Modifier.graphicsLayer {
                                val column = (item.value - 1) % puzzleSize
                                val row = (item.value - 1) / puzzleSize
                                transformOrigin = TransformOrigin(0f, 0f)
                                scaleX = puzzleSize.toFloat()
                                scaleY = puzzleSize.toFloat()
                                translationX = -(column * this.size.width)
                                translationY = -(row * this.size.height)
                            }.fillMaxSize()
                            when (state) {
                                is PuzzleViewModel.State.GalleryImage -> {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalPlatformContext.current)
                                            .data(state.image)
                                            .build(),
                                        contentDescription = contentDescription,
                                        contentScale = contentScale,
                                        modifier = modifier,
                                    )
                                }

                                else -> {
                                    Image(
                                        painter = painterResource("forest_small.jpg"),
                                        contentDescription = contentDescription,
                                        contentScale = contentScale,
                                        modifier = modifier
                                    )
                                }
                            }

                        }
                    }
                    Text(
                        text = if (!isEmptySquare) "${item.value}" else "",
                        style = TextStyle(fontSize = 24.sp, background = Color.White),
                    )
                }
            }
        }
    }
}

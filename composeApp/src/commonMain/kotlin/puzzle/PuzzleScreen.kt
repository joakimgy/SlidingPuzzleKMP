package puzzle

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
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel


object PuzzleScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = navigator.rememberNavigatorScreenModel { PuzzleViewModel() }
        val state by screenModel.state.collectAsState()

        var board by remember { mutableStateOf(generatePuzzle(solved = true)) }

        fun onPuzzleCompleted() {
            board = generatePuzzle()
            state.points++
        }

        Column(
            Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Point: ${state.points}")
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
                PuzzleView(board = board, state = state) {
                    board = board.moveSquare(it)
                }
            }
        }
    }
}

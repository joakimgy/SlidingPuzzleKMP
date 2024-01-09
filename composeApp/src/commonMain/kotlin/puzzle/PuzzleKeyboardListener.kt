package puzzle

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*

@Composable
fun PuzzleKeyboardListener(
    board: List<Int>,
    onSquareMove: (square: Int) -> Unit,
    onEnterPress: () -> Unit,
    content: @Composable () -> Unit,
) {
    val requester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        requester.requestFocus()
    }

    fun onKeyEvent(e: KeyEvent): Boolean {
        if (e.type != KeyEventType.KeyDown) {
            return false
        }
        val action: Int? = when (e.key) {
            Key.A, Key.DirectionLeft -> {
                board.squareIndexForKeyboardAction(PuzzleKeyboardAction.LEFT)
            }

            Key.D, Key.DirectionRight -> {
                board.squareIndexForKeyboardAction(PuzzleKeyboardAction.RIGHT)

            }

            Key.W, Key.DirectionUp -> {
                board.squareIndexForKeyboardAction(PuzzleKeyboardAction.UP)

            }

            Key.S, Key.DirectionDown -> {
                board.squareIndexForKeyboardAction(PuzzleKeyboardAction.DOWN)

            }

            Key.Enter -> {
                onEnterPress()
                return true
            }

            else -> {
                return false
            }
        }
        return if (action != null) {
            onSquareMove(action)
            true
        } else {
            false
        }
    }

    Box(
        Modifier
            .onKeyEvent { onKeyEvent(it) }
            .focusRequester(requester)
            .focusable()
    ) {
        content()
    }
}

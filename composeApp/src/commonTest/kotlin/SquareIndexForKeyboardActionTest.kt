import kotlin.test.Test
import kotlin.test.assertEquals

class SquareIndexForKeyboardActionTest {

    @Test
    fun moveLeftDoesNotMakeInvalidMove() {
        val testPuzzle: List<Int> = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.LEFT)
        assertEquals(null, result)
    }

    @Test
    fun moveLeftDoesValidMove() {
        val testPuzzle: List<Int> = listOf(1, 0, 2, 3, 4, 5, 6, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.LEFT)
        assertEquals(0, result)
    }

    @Test
    fun moveRightDoesNotMakeInvalidMove() {
        val testPuzzle: List<Int> = listOf(3, 1, 2, 5, 4, 0, 6, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.RIGHT)
        assertEquals(null, result)
    }

    @Test
    fun moveRightDoesValidMove() {
        val testPuzzle: List<Int> = listOf(3, 1, 2, 4, 0, 5, 6, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.RIGHT)
        assertEquals(5, result)
    }

    @Test
    fun moveDownDoesNotMakeInvalidMove() {
        val testPuzzle: List<Int> = listOf(3, 1, 2, 5, 4, 6, 0, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.DOWN)
        assertEquals(null, result)
    }

    @Test
    fun moveDownDoesValidMove() {
        val testPuzzle: List<Int> = listOf(3, 1, 2, 4, 0, 5, 6, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.DOWN)
        assertEquals(7, result)
    }

    @Test
    fun moveUpDoesNotMakeInvalidMove() {
        val testPuzzle: List<Int> = listOf(3, 1, 0, 5, 4, 6, 2, 7, 8)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.UP)
        assertEquals(null, result)
    }

    @Test
    fun moveUpDoesValidMove() {
        val testPuzzle: List<Int> = listOf(3, 1, 2, 4, 8, 5, 6, 7, 0)
        val result = testPuzzle.squareIndexForKeyboardAction(PuzzleKeyboardAction.UP)
        assertEquals(5, result)
    }
}
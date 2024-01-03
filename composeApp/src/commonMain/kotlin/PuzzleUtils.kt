import kotlin.math.sqrt

fun generatePuzzle(solved: Boolean = false): List<Int> {
    while (true) {
        val puzzle = (1 until PUZZLE_SIZE * PUZZLE_SIZE)
            .plus(0)
            .let { if (solved) it else it.shuffled() }
        if (puzzle.isSolvable()) {
            return puzzle
        }
    }
}

fun List<Int>.moveSquare(indexToMove: Int): List<Int> {
    return if (isNextToEmptySquare(indexToMove)) {
        val emptySquareIndex = this.indexOf(0)
        this.swap(emptySquareIndex, indexToMove)
    } else {
        this
    }
}

fun List<Int>.isNextToEmptySquare(index: Int): Boolean {
    val size = this.getSize()
    val emptySquareIndex = this.indexOf(0)
    return listOf(index + size, index - size, index + 1, index - 1).contains(emptySquareIndex)
}

fun List<Int>.isSolved() = this.dropLast(1).asSequence().zipWithNext { a, b -> a <= b }.all { it }

/** Calculate if the puzzle is solvable by counting inversions */
fun List<Int>.isSolvable(): Boolean {
    val order = this.filter { it != 0 }

    val size = this.getSize()
    var inverseCount = 0
    for (i in order.indices) {
        for (j in i + 1 until order.size) {
            if (order[i] > order[j]) {
                inverseCount++
            }
        }
    }

    /** Different logic for odd/even-numbered height
     * https://www.sitepoint.com/randomizing-sliding-puzzle-tiles/
     */
    return if (size % 2 == 1) {
        inverseCount % 2 == 0
    } else {
        val emptySquareRow = this.indexOf(0) / this.getSize()
        (inverseCount + size - (emptySquareRow + 1)) % 2 == 0
    }
}

fun List<Int>.getSize() = sqrt(this.size.toFloat()).toInt()

enum class PuzzleKeyboardAction {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

fun List<Int>.squareIndexForKeyboardAction(action: PuzzleKeyboardAction): Int? {
    val puzzleSize = this.getSize()
    val emptySquareIndex = this.indexOf(0)
    val column = emptySquareIndex % puzzleSize
    val row = emptySquareIndex / puzzleSize

    when (action) {
        PuzzleKeyboardAction.LEFT -> {
            if (column > 0) {
                return emptySquareIndex - 1
            }
        }

        PuzzleKeyboardAction.RIGHT -> {
            if (column < puzzleSize - 1) {
                return emptySquareIndex + 1
            }
        }

        PuzzleKeyboardAction.UP -> {
            if (row > 0) {
                return emptySquareIndex - puzzleSize
            }
        }

        PuzzleKeyboardAction.DOWN -> {
            if (row < puzzleSize - 1) {
                return emptySquareIndex + puzzleSize
            }
        }
    }
    return null
}
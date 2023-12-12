import kotlin.math.sqrt

fun generatePuzzle(solved: Boolean = false) =
    (1 until PUZZLE_SIZE * PUZZLE_SIZE).plus(0)
        .let { if (solved) it else it.shuffled() }

fun List<Int>.moveSquare(indexToMove: Int): List<Int> {
    return if (isNextToEmptySquare(indexToMove)) {
        val emptySquareIndex = this.indexOf(0)
        this.swap(emptySquareIndex, indexToMove)
    } else {
        println("Could not move square $indexToMove")
        this
    }
}

fun List<Int>.isNextToEmptySquare(index: Int): Boolean {
    val size = sqrt(this.size.toFloat()).toInt()
    val emptySquareIndex = this.indexOf(0)
    return listOf(index + size, index - size, index + 1, index - 1).contains(emptySquareIndex)
}

fun List<Int>.isSolved() = this.dropLast(1).asSequence().zipWithNext { a, b -> a <= b }.all { it }

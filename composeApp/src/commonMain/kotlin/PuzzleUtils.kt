import kotlin.math.sqrt

fun generatePuzzle() = List(PUZZLE_SIZE * PUZZLE_SIZE) { it }.shuffled()

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

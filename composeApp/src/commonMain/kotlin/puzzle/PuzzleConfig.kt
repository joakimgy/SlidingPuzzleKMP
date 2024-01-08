interface PuzzleConfig {
    val puzzleSize: Int
}

expect fun getPuzzleConfig(): PuzzleConfig
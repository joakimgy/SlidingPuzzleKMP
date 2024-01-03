class IOSConfig : PuzzleConfig {
    override val puzzleSize: Int = 3
}

actual fun getPuzzleConfig(): PuzzleConfig = IOSConfig()
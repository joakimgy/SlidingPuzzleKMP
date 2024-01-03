class JVMConfig : PuzzleConfig {
    override val puzzleSize: Int = 3
}

actual fun getPuzzleConfig(): PuzzleConfig = JVMConfig()
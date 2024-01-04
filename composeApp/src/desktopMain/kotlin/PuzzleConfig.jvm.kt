class JVMConfig : PuzzleConfig {
    override val puzzleSize: Int = 4
}

actual fun getPuzzleConfig(): PuzzleConfig = JVMConfig()
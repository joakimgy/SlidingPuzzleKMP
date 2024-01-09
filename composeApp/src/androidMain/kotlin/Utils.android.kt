import okio.FileSystem
import okio.Path.Companion.toPath

actual fun readFile(path: String): ByteArray {
    return FileSystem.SYSTEM.read(path.toPath()) {
        readUtf8()
    }.toByteArray()
}
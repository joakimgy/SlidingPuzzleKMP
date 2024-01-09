fun <T> List<T>.swap(index1: Int, index2: Int) =
    this.let {
        val mutableList = it.toMutableList()
        val tmp = mutableList[index1]
        mutableList[index1] = mutableList[index2]
        mutableList[index2] = tmp
        mutableList
    }.toList()

expect fun readFile(path: String): ByteArray
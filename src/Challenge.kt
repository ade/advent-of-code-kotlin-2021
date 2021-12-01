interface Challenge {
    val day: Int

    val part1: Solution
    val part2: Solution

    fun readInputAsList() = readInputAsList(day)
    fun readInputAsIntList() = readInputAsList().map { it.toInt() }
}
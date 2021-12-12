package base

import java.io.File

abstract class Challenge {
    abstract val day: Int

    open val part1: Solution? = null
    open val part2: Solution? = null
    open val both: Solution? = null

    fun fileAsList(file: String): List<String> = readDayFileAsList(day, file)

    private fun readDayFileAsList(day: Int, filename: String): List<String> {
        val paddedDay = day.toString().padStart(2, '0')
        return File("src/day${paddedDay}", "$filename$day.txt").readLines()
    }
}
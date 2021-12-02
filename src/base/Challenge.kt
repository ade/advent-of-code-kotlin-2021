package base

import java.io.File

interface Challenge {
    val day: Int

    val part1: Solution?
    val part2: Solution?

    fun fileAsList(file: String): List<String> = readDayFileAsList(day, file)

    private fun readDayFileAsList(day: Int, filename: String): List<String> {
        val paddedDay = day.toString().padStart(2, '0')
        return File("src/day${paddedDay}", "$filename$day.txt").readLines()
    }
}
package base

import java.io.File

interface Challenge {
    val day: Int

    val part1: Solution?
    val part2: Solution?

    fun readExampleAsList() = readExampleAsList(day)
    fun readInputAsList() = readInputAsList(day)
    fun readInputAsIntList() = readInputAsList().map { it.toInt() }

    /**
     * Reads lines from the given input txt file.
     */
    private fun readInputAsList(day: Int): List<String> {
        val dayString = day.toString().padStart(2, '0')
        return File("src/day${dayString}", "input$day.txt").readLines()
    }

    private fun readExampleAsList(day: Int): List<String> {
        val dayString = day.toString().padStart(2, '0')
        return File("src/day${dayString}", "example$day.txt").readLines()
    }
}
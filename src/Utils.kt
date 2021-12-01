import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInputAsList(day: Int): List<String> {
    val dayString = day.toString().padStart(2, '0')
    return File("src/day${dayString}", "input$day.txt").readLines()
}
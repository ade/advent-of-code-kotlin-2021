package base.day05

import base.Challenge
import base.Solution
import kotlin.math.max

private val INPUT_PATTERN = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

class Day5Part1And2(challenge: Challenge, val part2: Boolean): Solution(challenge) {
    override fun invoke() {
        check(solve(example) == if(!part2) 5 else 12)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val lines = input.map { parseLine(it) }
        val maxX = lines.maxOf { max(it.x1, it.x2) }
        val maxY = lines.maxOf { max(it.y1, it.y2) }
        val map = CloudMap(maxX+1, maxY+1)
        lines.forEach {
            paintLine(it, map)
        }
        return map.data.sumOf {
            it.fold(0) { acc, v ->
                if (v >= 2) acc + 1 else acc
            } as Int //weird IntelliJ issue if omitted
        }
    }

    private fun parseLine(s: String): Line {
        val (x1,y1,x2,y2) = INPUT_PATTERN.find(s)!!.destructured
        return Line(x1.toInt(),y1.toInt(),x2.toInt(),y2.toInt())
    }

    private fun paintLine(line: Line, map: CloudMap) {
        if(!part2 && !line.isStraight()) return

        line.project().forEach {
            map.paint(it.first, it.second)
        }
    }
}

private data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun isStraight() = x1 == x2 || y1 == y2

    fun project(): List<Pair<Int, Int>> {
        val xvals = (this.x1 through this.x2).toList()
        val yvals = (this.y1 through this.y2).toList()
        val items = max(xvals.size, yvals.size)
        return (0 until items).map { i ->
            Pair(xvals.getOrNull(i) ?: xvals[0], yvals.getOrNull(i) ?: yvals[0])
        }
    }
}
private data class CloudMap(val width: Int, val height: Int) {
    val data: Array<Array<Int>> = (0 until height).map { Array(width) { 0 } }.toTypedArray()

    fun get(x: Int, y: Int) = data[y][x]
    fun set(x: Int, y: Int, value: Int) {
        data[y][x] = value
    }

    fun paint(x: Int, y: Int) {
        set(x, y, get(x,y) + 1)
    }
}

/**
 * Kotlin doesn't afaik natively have a ".." or "until" function that will detect the direction to step,
 * so rather than "inverting" or checking direction of lines, we can step in any direction with this function.
 */
private infix fun Int.through(to: Int) =
    IntProgression.fromClosedRange(this, to, if (this > to) -1 else 1)
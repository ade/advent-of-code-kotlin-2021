package base.day09

import base.Challenge
import base.Solution

class Day9Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 1134)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val map = convert(input)

        val lowPoints = mutableListOf<Point>()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val point = Point(x,y)

                if(point.lowerThan(map, point.left) &&
                        point.lowerThan(map, point.right) &&
                        point.lowerThan(map, point.above) &&
                        point.lowerThan(map, point.below)) {
                    lowPoints.add(Point(x,y))
                }
            }
        }

        val basins = lowPoints.map {
            explore(map, it, mutableSetOf())
        }

        return basins
            .sortedBy { it.size }
            .reversed()
            .take(3)
            .map { it.size }
            .reduce { acc, it -> acc * it}
    }

    private fun convert(input: List<String>): Array<Array<Int>> {
        val width = input.first().length
        val height = input.size
        val map = Array(height) { Array(width) {0} }
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                map[y][x] = char.toString().toInt()
            }
        }
        return map
    }

    private fun explore(map: Array<Array<Int>>, point: Point, visited: MutableSet<Point>): Set<Point> {
        if(point.x < 0 ||
            point.y < 0 ||
            map.lastIndex < point.y ||
            map[0].lastIndex < point.x ||
            map[point.y][point.x] == 9 ||
            visited.contains(point)) return setOf()

        visited.add(point)

        val nextPoints = setOf(point.left, point.right, point.above, point.below)

        return setOf(point) + nextPoints.map { explore(map, it, visited) }.flatten()
    }
}

data class Point(val x: Int, val y: Int) {
    val left get() = copy(x = x - 1)
    val right get() = copy(x = x + 1)
    val above get() = copy(y = y - 1)
    val below get() = copy(y = y + 1)

    fun lowerThan(map: Array<Array<Int>>, other: Point): Boolean {
        if(other.x < 0 || other.x > map[0].lastIndex || other.y < 0 || other.y > map.lastIndex)
            return true

        return map[y][x] < map[other.y][other.x]
    }
}
package base.day09

import base.Challenge
import base.Solution

class Day9Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 15)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val width = input.first().length
        val height = input.size
        val map = Array(height) { Array(width) {0} }
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                map[y][x] = char.toString().toInt()
            }
        }

        var sum = 0
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val v = char.toString().toInt()
                val hasSmallerOrEqualNeighbor =
                    left(map,x,y)?.let { it <= v } == true ||
                    top(map,x,y)?.let { it <= v } == true ||
                    right(map,x,y)?.let { it <= v } == true ||
                    bottom(map,x,y)?.let { it <= v } == true

                if(!hasSmallerOrEqualNeighbor) {
                    sum += v + 1
                }
            }
        }
        return sum
    }

    private fun left(map: Array<Array<Int>>, x: Int, y: Int): Int? {
        if(x == 0) return null
        return map[y][x-1]
    }
    private fun top(map: Array<Array<Int>>, x: Int, y: Int): Int? {
        if(y == 0) return null
        return map[y-1][x]
    }
    private fun right(map: Array<Array<Int>>, x: Int, y: Int): Int? {
        if(x == map[y].lastIndex) return null
        return map[y][x+1]
    }
    private fun bottom(map: Array<Array<Int>>, x: Int, y: Int): Int? {
        if(y == map.lastIndex) return null
        return map[y+1][x]
    }
}
package base.day13

import base.Challenge
import base.Solution

class Day13Part1And2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example, true), 17)
        println("Result: ${solve(input, true)}")

        println("Part 2:")
        println("${solve(input, false)} folds")
    }

    private fun solve(data: List<String>, part1: Boolean): Int {
        val split = data.indexOfFirst { it == "" }
        val dots = data.take(split).map { it.split(',') }.map { it.map(String::toInt) }
        var instructions = data.takeLast(data.size - split - 1)
        if(part1) instructions = instructions.take(1)
        val width = dots.maxOf { it[0] } + 1
        val height = dots.maxOf { it[1] } + 1

        val map = Array(height) { Array(width) {0} }

        dots.forEach {
            map[it[1]][it[0]] = 1
        }

        var workmap = map
        instructions.forEach {
            val (foldAxis, foldIndex) = """fold along ([x|y])=(\d+)""".toRegex().find(it)!!.destructured
            workmap = fold(foldAxis == "y", foldIndex.toInt(), workmap)
        }

        if(!part1) {
            val charmap = listOf('.', 'X')
            workmap.forEach { println(it.map { charmap[it] }.joinToString("")) }
        }

        return workmap.sumOf { it.sum() }
    }

    private fun fold(foldY: Boolean, foldIndex: Int, map: Array<Array<Int>>): Array<Array<Int>> {
        if(foldY) {
            val bottom = map.takeLast(foldIndex).reversed()
            val result = map.take(foldIndex).mapIndexed { y, line ->
                line.mapIndexed { x, i ->
                    minOf(i + bottom[y][x], 1)
                }.toTypedArray()
            }.toTypedArray()

            return result
        } else {
            val right = map.map {
                it.takeLast(foldIndex).reversed()
            }

            val result = map
                .map {
                    it.take(foldIndex)
                }
                .mapIndexed { y, line ->
                    line.mapIndexed { x, i ->
                        minOf(i + right[y][x], 1)
                    }.toTypedArray()
                }
                .toTypedArray()

            return result
        }
    }
}
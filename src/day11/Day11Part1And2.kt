package base.day11

import base.Challenge
import base.Solution

class Day11Part1And2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example).part1, 1656)
        expect(solve(example).part2, 195)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Answers {
        val map = input.mapIndexed { y, line ->
            line.mapIndexed { x, it -> Octopus(x, y, it.toString().toInt(), false) }.toTypedArray()
        }.toTypedArray()

        var sum = 0
        var firstAllFlash = -1
        var part1sum = 0
        (1..999).forEachIndexed { _, step ->
            map.forEachIndexed { y, octopi ->
                octopi.forEachIndexed { x, octopus ->
                    octopus.beginStep()
                }
            }

            sum += map
                .map { octopi ->
                    octopi.map { octopus ->
                        octopus.flash(map)
                    }
                }
                .flatten()
                .sum()

            val allFlashedThisStep = map.map { line ->
                line.all { it.flashed }
            }.all { it }

            map.forEach { octopi ->
                octopi.forEach { octopus ->
                    octopus.endStep()
                }
            }

            if(allFlashedThisStep && firstAllFlash == -1) {
                firstAllFlash = step
            }
            if(step == 100) part1sum = sum
        }
        return Answers(part1sum, firstAllFlash)
    }
}

data class Answers(val part1: Int, val part2: Int)

data class Octopus(val x: Int, val y: Int, var energy: Int, var flashed: Boolean) {
    val left get() = copy(x = x - 1)
    val right get() = copy(x = x + 1)
    val above get() = copy(y = y - 1)
    val below get() = copy(y = y + 1)
    val topLeft get() = copy(y = y - 1, x = x - 1)
    val topRight get() = copy(y = y - 1, x = x + 1)
    val bottomLeft get() = copy(y = y + 1, x = x - 1)
    val bottomRight get() = copy(y = y + 1, x = x + 1)

    fun beginStep() {
        energy++
    }

    fun flash(map: Array<Array<Octopus>>): Int {
        var flashes = 0
        if(!flashed && energy > 9) {
            flashes = 1
            flashed = true
            flashes += listOf(left, right, above, below, topLeft, topRight, bottomLeft, bottomRight)
                .filter { it.inside(map) }
                .map { map[it.y][it.x] }
                .onEach { it.energy++ }
                .sumOf { it.flash(map) }
        }
        return flashes
    }

    fun inside(map: Array<Array<Octopus>>): Boolean {
        return y >= 0 && y <= map.lastIndex && x >= 0 && x <= map[y].lastIndex
    }

    fun endStep() {
        if(flashed) {
            energy = 0
        }
        flashed = false
    }
}
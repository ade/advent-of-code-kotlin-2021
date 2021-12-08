package base.day08

import base.Challenge
import base.Solution

class Day8Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 26)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val outputValues = input.map {
            it.split(" | ")[1].split(' ')
        }

        return outputValues.sumOf { line ->
            line.sumOf {
                when(it.length) {
                    2,3,4,7 -> 1
                    else -> 0
                }.toInt()
            }
        }
    }
}
package base.day07

import base.Challenge
import base.Solution
import kotlin.math.sign


class Day7Solution(challenge: Challenge, val part2: Boolean): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), if(!part2) 37 else 168)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val crabs = input
            .first()
            .split(',')
            .map(String::toInt)
            .map { Crab(part2, it) }

        val crabPositions = crabs
            .map { it.position }
            .distinct()

        val positions = (crabPositions.minOrNull()!!..crabPositions.maxOrNull()!!).toList()

        val result = positions.minOf { target ->
            crabs
                .map { it.copy() }
                .sumOf { it.moveTo(target) }
        }

        return result
    }

    data class Crab(val part2: Boolean, var position: Int, var moves: Int = 0, var fuelSpent: Int = 0) {
        fun moveTo(target: Int): Int {
            while(target != position) {
                position += (target-position).sign
                moves++
                if(!part2) {
                    fuelSpent++
                } else {
                    fuelSpent += moves
                }
            }

            return fuelSpent
        }
    }
}
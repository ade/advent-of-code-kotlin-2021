package day02

import base.Challenge
import base.Solution

class Day2Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(example) == 900)
        println("Solution: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        var aim = 0
        var horizontal = 0
        var depth = 0
        input.forEach {
            val ( type, value ) = it.split(" ")
            val amount = value.toInt()
            when(type) {
                "up" -> aim -= amount
                "down" -> aim += amount
                "forward" -> {
                    horizontal += amount
                    depth += aim * amount
                }
            }
        }
        return horizontal * depth
    }
}
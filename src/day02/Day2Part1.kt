package day02

import base.Challenge
import base.Solution

class Day2Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(example) == 150)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val up = getSum(input.filter { it.startsWith("up") })
        val down = getSum(input.filter { it.startsWith("down") })
        val forward = getSum(input.filter { it.startsWith("forward") })
        val depth = down - up
        return depth * forward
    }

    private fun getSum(list: List<String>): Int {
        return list
            .map { it.split(" ").last() }
            .sumOf { it.toInt() }
    }
}
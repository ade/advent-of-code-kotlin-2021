package day02

import base.Challenge
import base.Solution

class NavigationSum(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        val example = readExampleAsList()
        check(solve(example) == 150)

        val input = readInputAsList()
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
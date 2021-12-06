package base.day06

import base.Challenge
import base.Solution

class Day6Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(example) == 5934)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val fish = input
            .first()
            .split(',')
            .map(String::toInt)
            .toMutableList()

        val iterations = 80
        repeat(iterations) {
            val children = mutableListOf<Int>()
            fish.forEachIndexed { index, i ->
                if(i == 0) {
                    children.add(8)
                    fish[index] = 6
                } else {
                    fish[index] = i - 1
                }
            }
            fish.addAll(children)
        }
        return fish.size
    }
}
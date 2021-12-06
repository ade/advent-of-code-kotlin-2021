package base.day06

import base.Challenge
import base.Solution

class Day6Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 26984457539L)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Long {
        val seed = input
            .first()
            .split(',')
            .map(String::toLong)
            .toMutableList()

        val fishes = Array(9) {0L}
        fishes.forEachIndexed { index, i ->
            fishes[index] = seed.map { if(it == index.toLong()) 1L else 0L }.sum()
        }

        repeat(256) {
            val zeroes = fishes[0]
            (0..7).map {
                fishes[it] = fishes[it+1]
            }
            fishes[8] = zeroes
            fishes[6] = fishes[6] + zeroes
        }
        return fishes.sumOf { it }
    }
}
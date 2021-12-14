package day14

import base.Challenge
import base.Solution

class Day14Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 1588)
        println("Result: ${solve(input)}")
    }

    private fun solve(data: List<String>): Int {
        var polymer = data.first().toCharArray()

        val map = data.drop(2).map {
            val (pair, result) = """(..) -> (.)""".toRegex().find(it)!!.destructured
            pair to result.first()
        }.toMap()

        for(i in 0 until 10) {
            val results = polymer
                .mapIndexed { index, c ->
                    val next = if(polymer.lastIndex == index) null else polymer[index + 1]
                    map["$c$next"]
                }.filterNotNull()

            polymer = polymer
                .zip(results)
                .map { "${it.first}${it.second}" }
                .joinToString("")
                .toCharArray() + polymer[polymer.lastIndex]
        }

        val occurrences = polymer
            .distinct()
            .map { atom ->
                atom to polymer.count { it == atom }
            }

        val mostCommon = occurrences.maxOf { it.second }
        val leastCommon = occurrences.minOf { it.second }

        return mostCommon - leastCommon
    }
}

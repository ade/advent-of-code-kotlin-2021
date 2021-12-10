package base.day10

import base.Challenge
import base.Solution
import java.util.*

class Day10Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 26397)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        return input.sumOf { solveLine(it) }
    }

    private fun solveLine(line: String): Int {
        val stack = Stack<Char>()
        val pairs = mapOf(
            '{' to '}',
            '(' to ')',
            '[' to ']',
            '<' to '>'
        )
        val scoring = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137,
        )

        line.forEach { token ->
            val opener = pairs.containsKey(token)
            if(opener) {
                stack.push(token)
            } else {
                val pair = pairs.entries.first { it.value == token }
                if(stack.peek() == pair.key) {
                    stack.pop()
                } else {
                    return scoring[token]!!
                }
            }
        }
        return 0
    }
}
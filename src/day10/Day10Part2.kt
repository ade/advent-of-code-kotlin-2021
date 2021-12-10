package base.day10

import base.Challenge
import base.Solution
import java.util.*

class Day10Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 288957)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Long {
        val scores = input
            .map { solveLine(it) }
            .filter { it != 0L }
            .sorted()
        
        return scores[scores.lastIndex/2]
    }

    private fun solveLine(line: String): Long {
        val stack = Stack<Char>()
        val pairs = mapOf(
            '{' to '}',
            '(' to ')',
            '[' to ']',
            '<' to '>'
        )
        val scoring = mapOf(
            ')' to 1L,
            ']' to 2L,
            '}' to 3L,
            '>' to 4L,
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
                    return 0
                }
            }
        }

        //Line is now parsed successfully

        val solution = stack
            .toList()
            .reversed()
            .map {
                scoring[pairs[it]!!]!!
            }

        return solution.reduce { acc, it ->
            acc * 5 + it
        }
    }
}
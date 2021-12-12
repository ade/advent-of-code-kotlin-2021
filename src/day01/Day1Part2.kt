package day01

import base.Challenge
import base.Solution

class Day1Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(exampleAsInts) == 5)
        println("The number of increments in three-value windows is: ${solve(inputAsInts)}")
    }

    private fun solve(input: List<Int>): Int {
        val windows = input.mapIndexedNotNull() { index, i ->
            if(input.lastIndex < index + 2)
                null
            else
                i + input[index+1] + input[index+2]
        }

        return windows.reduceAdjacentToInt { accumulator, previous, current ->
            if(current > previous)
                accumulator + 1
            else
                accumulator
        }
    }
}
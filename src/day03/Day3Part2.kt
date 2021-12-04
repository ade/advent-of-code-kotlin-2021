package base.day03

import base.Challenge
import base.Solution

class Day3Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(exampleAsBinaryInts) == 230)
        println("Result: ${solve(inputAsBinaryInts)}")
    }

    private fun solve(input: List<Int>): Int {
        val highestBit = input.maxOrNull()!!.takeHighestOneBit()

        val oxygen = bitSearch(highestBit, input, commonMode = true)
        val co2 = bitSearch(highestBit, input, commonMode = false)

        return oxygen * co2
    }

    private fun bitSearch(bit: Int, input: List<Int>, commonMode: Boolean): Int {
        val ones = input.count { it and bit != 0 }
        val zeroes = input.size - ones

        val desired = if(commonMode) {
            if(ones >= zeroes) bit else 0
        } else {
            if(ones >= zeroes) 0 else bit
        }

        val remaining = input.filter { it and bit == desired }

        return if(remaining.size == 1)
            remaining.first()
        else
            bitSearch(bit.shr(1), remaining, commonMode)
    }
}
package base.day03

import base.Challenge
import base.Solution
import kotlin.math.pow

class Day3Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(exampleAsBinaryInts) == 198)
        println("Result: ${solve(inputAsBinaryInts)}")
    }

    private fun solve(input: List<Int>): Any {
        //Figure out how many bits are in use (assuming there is at least one bit set in the first column)
        val width = Int.SIZE_BITS - input.maxOrNull()!!.countLeadingZeroBits()

        //Generate a list of bits that matches the amount of bits in use
        val bits = generateSequence(1) {
            val result = it * 2
            if(result >= 2.toDouble().pow(width)) null else result
        }

        //Count the bits in each column and convert back to an int by setting each bit.
        val gamma = bits.map { bit ->
            val ones = input.count { it and bit != 0 }
            val zeroes = input.size - ones
            if(ones > zeroes) bit else 0
        }.reduce { acc, bit ->
            acc or bit
        }

        //Generate a bit mask that matches the amount of bits in use
        val bitMask = bits.reduce { acc, i -> i or acc }

        //Limit the inversion output by the bits that are actually in use.
        val epsilon = gamma.inv() and bitMask

        return gamma * epsilon
    }
}
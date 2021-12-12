package day01

import base.Challenge
import base.Solution

class Day1Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(exampleAsInts) == 7)
        println("The number of increments is: ${solve(inputAsInts)}")
    }

    private fun solve(inputAsInts: List<Int>): Int {
        return inputAsInts.reduceAdjacentToInt { accumulator, previous, current ->
            if(current > previous)
                accumulator + 1
            else
                accumulator
        }
    }
}

inline fun <T> Iterable<T>.reduceAdjacentToInt(operation: (accumulator: Int, previous: T, current: T) -> Int): Int {
    val iterator = this.iterator()
    if (!iterator.hasNext()) throw UnsupportedOperationException("Empty collection can't be reduced.")
    var accumulator = 0
    var previous = iterator.next()
    if (!iterator.hasNext()) throw UnsupportedOperationException("Collection must have at least two items.")
    while (iterator.hasNext()) {
        val current = iterator.next()
        accumulator = operation(accumulator, previous, current)
        previous = current
    }
    return accumulator
}
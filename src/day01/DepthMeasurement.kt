package day01

import base.Challenge
import base.Solution

class DepthMeasurement(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        val input = challenge.readInputAsIntList()

        val result = input.reduceAdjacentToInt { accumulator, previous, current ->
            if(current > previous)
                accumulator + 1
            else
                accumulator
        }

        println("The number of increments is: $result")
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
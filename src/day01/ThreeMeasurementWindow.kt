package day01

import Challenge
import Solution

class ThreeMeasurementWindow(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        val input = challenge.readInputAsIntList()
        val windows = input.mapIndexedNotNull() { index, i ->
            if(input.lastIndex < index + 2)
                null
            else
                i + input[index+1] + input[index+2]
        }

        val result = windows.reduceAdjacentToInt { accumulator, previous, current ->
            if(current > previous)
                accumulator + 1
            else
                accumulator
        }

        println("The number of increments in three-value windows is: $result")
    }
}
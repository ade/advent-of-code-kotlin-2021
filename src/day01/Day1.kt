package day01

import Challenge
import Solution

class Day1: Challenge {
    override val day: Int = 1

    override val part1: Solution = DepthMeasurement(this)
    override val part2: Solution = ThreeMeasurementWindow(this)
}
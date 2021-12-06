package base.day05

import base.Challenge

class Day5: Challenge {
    override val day = 5
    override val part1 = Day5Part1And2(this, false)
    override val part2 = Day5Part1And2(this, true)
}
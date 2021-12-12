package base.day12

import base.Challenge
import day12.Day12Solution

class Day12: Challenge() {
    override val day = 12
    override val part1 = Day12Solution(this, false)
    override val part2 = Day12Solution(this, true)
}
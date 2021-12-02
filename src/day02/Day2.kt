package day02

import base.Challenge

class Day2: Challenge {
    override val day: Int = 2
    override val part1 = NavigationSum(this)
    override val part2 = AimedSum(this)
}
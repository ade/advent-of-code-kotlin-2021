package base

import base.day03.Day3
import base.day04.Day4
import base.day05.Day5
import base.day06.Day6
import base.day07.Day7
import base.day08.Day8
import base.day09.Day9
import base.day10.Day10
import base.day11.Day11
import base.day12.Day12
import base.day13.Day13
import day01.Day1
import day02.Day2
import day14.Day14

fun main(args: Array<String>) {
    val implementations: Map<Int, () -> Challenge> = mapOf(
        1 to { Day1() },
        2 to { Day2() },
        3 to { Day3() },
        4 to { Day4() },
        5 to { Day5() },
        6 to { Day6() },
        7 to { Day7() },
        8 to { Day8() },
        9 to { Day9() },
        10 to { Day10() },
        11 to { Day11() },
        12 to { Day12() },
        13 to { Day13() },
        14 to { Day14() }
    )

    val day = args.firstOrNull()?.toInt() ?: run {
        println("Specify a day!")
        return
    }

    val challenge = implementations[day]?.invoke() ?: run {
        println("Day $day is not implemented!")
        return
    }

    println("Day $day")
    challenge.both?.let { solution ->
        solution()
    } ?: run {
        println("Part 1")
        challenge.part1?.invoke() ?: println("Not implemented")
        println("Part 2")
        challenge.part2?.invoke() ?: println("Not implemented")
    }
}

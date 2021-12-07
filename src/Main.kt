package base

import base.day03.Day3
import base.day04.Day4
import base.day05.Day5
import base.day06.Day6
import base.day07.Day7
import day01.Day1
import day02.Day2

fun main(args: Array<String>) {
    val implementations: Map<Int, () -> Challenge> = mapOf(
        1 to { Day1() },
        2 to { Day2() },
        3 to { Day3() },
        4 to { Day4() },
        5 to { Day5() },
        6 to { Day6() },
        7 to { Day7() }
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
    println("Part 1")
    challenge.part1?.invoke() ?: println("Not implemented")
    println("Part 2")
    challenge.part2?.invoke() ?: println("Not implemented")
}

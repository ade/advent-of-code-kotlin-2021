package base

import base.day03.Day3
import day01.Day1
import day02.Day2

fun main(args: Array<String>) {
    val implementations: Map<Int, () -> Challenge> = mapOf(
        1 to { Day1() },
        2 to { Day2() },
        3 to { Day3() }
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

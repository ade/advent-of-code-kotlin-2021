import day01.Day1

fun main(args: Array<String>) {
    val implementations: Map<Int, () -> Challenge> = mapOf(
        1 to { Day1() }
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
    challenge.part1()
    println("Part 2")
    challenge.part2()
}

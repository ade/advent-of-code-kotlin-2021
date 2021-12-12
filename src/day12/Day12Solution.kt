package day12

import base.Challenge
import base.Solution

class Day12Solution(challenge: Challenge, val part2: Boolean): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), if(!part2) 226 else 3509) // "Even larger" example
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val links = mutableMapOf<Cave, List<Cave>>()

        input.forEach {
            val ids = it.split('-')
            val left = Cave(ids[0])
            val right = Cave(ids[1])
            links[left] = links.getOrPut(left) { listOf() } + right
            links[right] = links.getOrPut(right) { listOf() } + left
        }

        val start = Cave("start")

        return links[start]!!
            .map {
                pathFinder(links, listOf(start), it)
            }
            .flatten()
            .map { it.map { it.id }.reduce { acc, s -> "$acc-$s" } }
            .distinct()
            .size
    }

    fun pathFinder(links: Map<Cave, List<Cave>>, history: List<Cave>, current: Cave): List<List<Cave>> {
        if(current.end) return listOf(history + current)

        val exits = links[current]!!.filter {
            it.big ||
                    if (part2) {
                        when {
                            (it.start || it.end) -> !history.contains(it)
                            history.contains(it) -> !hasBeenTwiceToOneSmallCave(history + current)
                            else -> true
                        }
                    } else
                        !history.contains(it)
        }

        return exits.map { exit ->
            pathFinder(links, history + current, exit)
        }.flatten()
    }

    private fun hasBeenTwiceToOneSmallCave(history: List<Cave>): Boolean {
        val counter = mutableMapOf<String, Int>()
        history
            .filter { !it.big }
            .forEach {
                counter[it.id] = counter.getOrDefault(it.id, 0) + 1
            }
        return counter.any { it.value > 1 }
    }
}

data class Cave(val id: String) {
    val big: Boolean
        get() = id.toUpperCase() == id

    val end: Boolean
        get() = id == "end"

    val start: Boolean
        get() = id == "start"
}
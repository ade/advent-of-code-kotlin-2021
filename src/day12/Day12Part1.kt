package day12

import base.Challenge
import base.Solution

class Day12Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 226)
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
        val allExits = links[current]!!

        //Don't allow re-entering previous room or going to a small room more than once
        val exits = allExits.filter { it.big || !history.contains(it) }

        return exits.map { exit ->
            when {
                exit.end -> {
                    listOf((history + current) + exit)
                }
                else -> pathFinder(links, history + current, exit)
            }
        }.flatten()
    }
}

data class Cave(val id: String) {
    val big: Boolean
        get() = id.toUpperCase() == id

    val end: Boolean
        get() = id == "end"
}
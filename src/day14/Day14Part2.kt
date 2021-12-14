package base.day14

import base.Challenge
import base.Solution

class Day14Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 2188189693529L)
        println("Result: ${solve(input)}")
    }

    private fun solve(data: List<String>): Long {
        val template = data.first()

        val insertionRules = data.drop(2).associate {
            val (pair, result) = """(..) -> (.)""".toRegex().find(it)!!.destructured

            //Create a entry <String,Char> for each rule
            //e.g. "CH" -> 'B'
            pair to result.first()
        }

        val insertionsMap = insertionRules.map { rule ->
            val spawn = "${rule.key.first()}${rule.value}${rule.key.last()}"

            // Pre-calculate which child pairs each pair will generate in an entry <String, List<String>>
            // e.g. "CH" -> ["CB", "BH"]
            rule.key to spawn.windowed(2)
        }.toMap()

        //Initialize counter of all possible pairs. The rules are assumed to cover all possible pairs.
        val pairCounts = insertionRules.map {
            it.key to 0L
        }.toMap().toMutableMap()

        //Populate the counter from the template
        template.windowed(2).forEach {
            pairCounts[it] = pairCounts[it]!! + 1L
        }

        for(i in 0 until 40) {
            val copy = pairCounts.toMap()
            copy.forEach { entry ->
                val parentPair = entry.key
                val parentOccurrences = entry.value

                insertionsMap[parentPair]!!.forEach { childPair ->
                    //Each parent generates one of each child
                    pairCounts[childPair] = pairCounts[childPair]!! + parentOccurrences
                }

                // Each original pair now split by the inserted character, so it occurs one time less per occurrence
                pairCounts[parentPair] = pairCounts[parentPair]!! - parentOccurrences
            }
        }

        val elements = pairCounts
            .map { it.key.toCharArray().toList() }
            .flatten()
            .distinct()
            .associateWith { 0L }
            .toMutableMap()

        //Count occurrence of all elements
        pairCounts.forEach { item ->
            item.key.toCharArray().map {
                elements[it] = elements[it]!! + item.value
            }
        }

        //The first and last elements occur "once more" as they exist without belonging in a pair.
        val first = template.first()
        val last = template.last()
        elements[first] = elements[first]!! + 1L
        elements[last] = elements[last]!! + 1L

        //Now we halve all the occurrences, simulating a "collapse" where pairs overlap.
        val occurrences = elements.map { it.value / 2L }

        val mostCommon = occurrences.maxOf { it }
        val leastCommon = occurrences.minOf { it }

        return mostCommon - leastCommon
    }
}
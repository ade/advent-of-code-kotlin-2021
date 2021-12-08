package base.day08

import base.Challenge
import base.Solution

class Day8Part2(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        expect(solve(example), 61229)
        println("Result: ${solve(input)}")
    }

    private fun solve(input: List<String>): Int {
        val lines = input.map {
            val parts = it.split(" | ")
            Pair(parts[0].split(' '), parts[1].split(' '))
        }

        return lines.sumOf { line ->
            Key.of(line.first).decode(line.second)
        }
    }
}

/**
 *  0000
 * 1    2
 * 1    2
 *  3333
 * 4    5
 * 4    5
 *  6666
 */
private data class Key(val key: List<Char>) {
    private val digitLayouts = listOf(
        "012456", "25", "02346", "02356", "1235", "01356", "013456", "025", "0123456", "012356"
    )
    private val sortedDigitKeyMap: Map<String, Int> by lazy {
        buildMap { (0..9).forEach { put(sortedDigitKeyString(it), it) } }
    }

    fun decode(values: List<String>): Int {
        val digits = values.map { decodeDigit(it) }
        return digits.joinToString("").toInt()
    }

    private fun sortedDigitKeyString(d: Int): String {
        return digitLayouts[d]
            .map { key[it.toString().toInt()] }
            .sorted()
            .joinToString("")
    }

    private fun decodeDigit(s: String): Int {
        return sortedDigitKeyMap[String(s.toCharArray().sortedArray())]!!
    }

    companion object {
        fun of(s: List<String>): Key {
            val key = Array(7) { 'X' }
            val one = s.find { it.length == 2 }!!.toSet()
            val seven = s.find { it.length == 3 }!!.toSet()
            val four = s.find { it.length == 4 }!!.toSet()
            val eight = s.find { it.length == 7 }!!.toSet()

            key[0] = (seven - one).first()
            val seg1and3 = four - one
            val seg4and6 = eight - seven - four
            val nine = s.first {
                it.toSet().containsAll(seven + seg1and3) && it.length == 6
            }.toSet()
            key[4] = (eight - nine).first()
            key[6] = (seg4and6 - setOf(key[4])).first()
            val three = s.first {
                it.toSet().containsAll(seven + key[6]) && it.length == 5
            }.toSet()
            key[1] = (seg1and3 - three).first()
            key[3] = (seg1and3 - key[1]).first()
            val five = s.first {
                it.length == 5 && nine.containsAll(it.toSet()) && !it.toSet().containsAll(three)
            }.toSet()
            key[2] = (one - five).first()
            key[5] = (one - key[2]).first()

            check(String(key.sorted().toCharArray()) == "abcdefg")

            return Key(key.toList())
        }
    }
}
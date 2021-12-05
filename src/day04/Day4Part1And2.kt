package base.day04

import base.Challenge
import base.Solution

class Day4Part1And2(challenge: Challenge, private val part2: Boolean): Solution(challenge) {
    override fun invoke() {
        if(!part2)
            check(solve(example) == 4512)
        else
            check(solve(example) == 1924)

        println("Result: ${solve(input)}")
    }

    fun solve(input: List<String>): Int {
        val drawOrder = input.first().split(',').map(String::toInt)
        val boardsCount = (input.size-1) / 6

        val boardIndexes = (0 until boardsCount).map {
            it * 6 + 2
        }

        val boards = boardIndexes.map {
            Board.of(input.subList(it, it+5))
        }

        val winners = mutableSetOf<Board>()
        drawOrder.forEach { draw ->
            boards.filter { board ->
                board.consume(draw)
            }.forEach {
                winners.add(it)
            }
        }

        return if(!part2) {
            winners.first().winResult
        } else {
            winners.last().winResult
        }
    }
}

private data class Cell(var number: Int, var checked: Boolean = false)

private class Board {
    private val items = (1..5).map {
        (1..5).map { Cell(0) }
    }

    var winResult = 0
        private set

    fun consume(draw: Int): Boolean {
        items.forEach { row ->
            row.forEach {
                if(it.number == draw) it.checked = true
            }
        }

        if(winResult == 0) {
            val score = score()
            if(score > 0) {
                winResult = score * draw
            }
        }

        return winResult != 0
    }

    private fun score(): Int {
        val winning = (0..4).any { rowChecked(it) } || (0..4).any { columnChecked(it) }

        if(!winning) return 0

        return items.flatten().filter { !it.checked }.map { it.number }.sum()
    }

    private fun rowChecked(y: Int): Boolean {
        return (items[y].none { !it.checked })
    }

    private fun columnChecked(x: Int): Boolean {
        return ((0..4).map { items[it][x] }.none { !it.checked })
    }

    private fun setValue(x: Int, y: Int, value: Int) {
        items[y][x].number = value
    }

    companion object {
        fun of(list: List<String>): Board {
            check(list.size == 5)
            val board = Board()

            list.forEachIndexed { y, it ->
                val values = """(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".toRegex().find(it)!!.groupValues
                (1..5).forEach { x ->
                    board.setValue(x-1, y, values[x].toInt())
                }
            }

            return board
        }
    }
}
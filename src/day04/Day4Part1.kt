package base.day04

import base.Challenge
import base.Solution

class Day4Part1(challenge: Challenge): Solution(challenge) {
    override fun invoke() {
        check(solve(example) == 4512)
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

        return drawOrder.firstNotNullOf { draw ->
            val winnerScore = boards.maxOf { board ->
                board.consume(draw)
                board.score()
            }

            if(winnerScore != 0) winnerScore * draw else null
        }
    }
}

private data class Cell(var number: Int, var checked: Boolean = false)

private class Board {
    private val items = (1..5).map {
        (1..5).map { Cell(0) }
    }

    fun consume(draw: Int) {
        items.forEach { row ->
            row.forEach {
                if(it.number == draw) it.checked = true
            }
        }
    }

    fun score(): Int {
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
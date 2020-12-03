package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day

class Day3 : Day<String>(3) {
    override fun partOne(input: String) = traverse(input).let(this::countPresents)
    override fun partTwo(input: String): Any {
        val santaInput = StringBuilder()
        val robotInput = StringBuilder()
        input.forEachIndexed { index, c ->
            (if (index % 2 == 0) santaInput else robotInput).append(c)
        }
        val result = HashMap<Position, House>()
        traverse(santaInput.toString()).forEach { (k, v) ->
            result.merge(k, v) { v1, v2 -> v1.also { it.presents += v2.presents }}
        }
        traverse(robotInput.toString()).forEach {(k, v) ->
            result.merge(k, v) { v1, v2 -> v1.also { it.presents += v2.presents }}
        }
        return countPresents(result)
    }

    private fun countPresents(map: Map<Position, House>): Int = map.count { it.value.presents >= 1 }
    private fun traverse(input: String): HashMap<Position, House> = hashMapOf(
        Position(0, 0) to House()
    ).also { map ->
        var x = 0
        var y = 0
        input.mapNotNull {
            when (it) {
                '^' -> y++; 'v' -> y--
                '>' -> x++; '<' -> x--
                else -> return@mapNotNull null
            }
            map.compute(Position(x, y)) { _, v ->
                v?.also { it.presents++ } ?: House()
            }
        }
    }

    override fun adaptInput(input: String) = input

    private data class Position(val x: Int, val y: Int)
    private class House(var presents: Int = 1)
}
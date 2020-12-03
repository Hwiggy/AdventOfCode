package me.hwiggy.aoc.y2020

import me.hwiggy.aoc.types.Day

class Day3 : Day<Day3.Slope>(3) {
    override fun partOne(input: Slope) = countTrees(input, 1, 3)

    override fun partTwo(input: Slope) = listOf(
        1 to 1, 1 to 3, 1 to 5, 1 to 7, 2 to 1
    ).map { (dY, dX) -> countTrees(input, dY, dX) }.reduce(Long::times)

    private fun countTrees(slope: Slope, dY: Int, dX: Int): Long {
        var counter = if (slope.initial()) 1L else 0L
        while (true) {
            val isTree = slope.navigate(dY, dX) ?: break
            if (isTree) counter++
        }
        return counter.also { slope.reset() }
    }

    override fun adaptInput(input: String) = input.split("\n").map(String::toCharArray).let(::Slope)

    class Slope(private val grid: List<CharArray>) {
        var y: Int = 0
        var x: Int = 0

        fun initial() = grid[0][0] == '#'
        fun navigate(dY: Int, dX: Int): Boolean? {
            y += dY
            val row = grid.getOrNull(y) ?: return null
            x = (x + dX) % row.size
            return grid[y][x] == '#'
        }

        fun reset() { y = 0; x = 0 }
    }
}
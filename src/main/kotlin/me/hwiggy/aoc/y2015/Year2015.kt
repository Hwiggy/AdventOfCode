package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Year

class Year2015 : Year(2015) {
    override val days = listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5(),
        Day6(),
        Day7(),
        Day8(),
        Day9()
    )
}
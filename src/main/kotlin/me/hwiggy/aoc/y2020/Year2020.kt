package me.hwiggy.aoc.y2020

import me.hwiggy.aoc.types.Year

class Year2020 : Year(2020) {
    override val days = listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5()
    )
}
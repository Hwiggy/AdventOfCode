package me.hwiggy.aoc.y2020

import me.hwiggy.aoc.types.Day
import java.lang.IllegalStateException

class Day5 : Day<List<Day5.Seat>>(5) {
    override fun partOne(input: List<Seat>) = input.last().id
    override fun partTwo(input: List<Seat>): Any {
        val min = input.first().id
        val max = input.last().id
        for (it in min..max) {
            val index = it - min
            if (input[index].id != it) return it
        }
        throw IllegalStateException("Could not find seat!")
    }

    override fun adaptInput(input: String) = input.split("\n").map(::Seat).sortedBy(Seat::id)

    class Seat(pass: String) {
        private val row = Integer.parseInt(
            pass.substring(0, 7).replace("F", "0").replace("B", "1"), 2
        )
        private val column = Integer.parseInt(
            pass.substring(7, 10).replace("L", "0").replace("R", "1"), 2
        )
        val id = (row * 8) + column
    }
}
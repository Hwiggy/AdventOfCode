package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day

class Day9 : Day<Unit>(9) {
    override fun partOne(input: Unit): Any {
        TODO("Not yet implemented")
    }

    override fun partTwo(input: Unit): Any {
        TODO("Not yet implemented")
    }

    private val regex = "(\\w+) to (\\w+) = (\\d+)".toRegex()
    override fun adaptInput(input: String) {
        TODO("Not yet implemented")
    }

    private class Node(val src: String, val dest: String, val dist: Int)
}
package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day
import java.lang.IllegalArgumentException

class Day1 : Day<String>(1) {
    override fun partOne(input: String) = input.chars().map { if (it == '('.toInt()) 1 else -1 }.sum()
    override fun partTwo(input: String): Any {
        var floor = 0
        val chars = input.toCharArray()
        for(i in 0..chars.size) {
            floor += if (chars[i] == '(') 1 else -1
            if (floor == -1) return i + 1
        }
        throw IllegalArgumentException("Santa never reached the basement!")
    }

    override fun adaptInput(input: String) = input
}
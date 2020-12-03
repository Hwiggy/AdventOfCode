package me.hwiggy.aoc.y2020

import me.hwiggy.aoc.types.Day

class Day1 : Day<List<Int>>(1) {
    override fun partOne(input: List<Int>): Any {
        input.forEach { a ->
            input.forEach { b ->
                if (a + b == 2020) return a * b
            }
        }
        throw IllegalStateException("Could not find a match!")
    }

    override fun partTwo(input: List<Int>): Any {
        input.forEach { a ->
            input.forEach { b ->
                input.forEach { c ->
                    if (a + b + c == 2020) return a * b * c
                }
            }
        }
        throw IllegalStateException("Could not find a match!")
    }

    override fun adaptInput(input: String) = input.split("\n").map(String::toInt)
}
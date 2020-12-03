package me.hwiggy.aoc.y2020

import me.hwiggy.aoc.types.Day

class Day2 : Day<List<Day2.Password>>(2) {
    override fun partOne(input: List<Password>) = input.count(Password::validFirst)
    override fun partTwo(input: List<Password>) = input.count(Password::validSecond)

    private val regex = "(\\d+)-(\\d+) (\\w): (\\w+)".toRegex()
    override fun adaptInput(input: String) = input.split("\n").map {
        val (min, max, target, password) = regex.matchEntire(it)!!.destructured
        Password(min.toInt(), max.toInt(), target.single(), password)
    }

    data class Password(val min: Int, val max: Int, val target: Char, val password: String) {
        fun validFirst() = password.count { it == target } in min..max
        fun validSecond() = (password[min - 1] == target) xor (password[max - 1] == target)
    }
}
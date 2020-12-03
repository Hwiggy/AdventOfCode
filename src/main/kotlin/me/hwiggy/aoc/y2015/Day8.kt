package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day

class Day8 : Day<List<String>>(8) {
    override fun partOne(input: List<String>) = input.map { it to unescape(it) }.let(this::reduceToDifference)

    private val hexCodes = "\\\\x([a-fA-F0-9]{2})".toRegex()
    private fun unescape(input: String) =
        input.substring(1, input.length - 1)
            .replace("\\\"", "\"")
            .replace("\\\\", "\\")
            .replace(hexCodes) { "${Integer.parseInt(it.groupValues[1], 16).toChar()}" }

    override fun partTwo(input: List<String>) = input.map { escape(it) to it }.let(this::reduceToDifference)

    private fun escape(input: String) =
        input.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .let { "\"$it\"" }

    private fun reduceToDifference(pairs: List<Pair<String, String>>) =
        pairs.map { (a, b) -> a.length to b.length }.reduce { a, b ->
            val (c, d) = a
            val (e, f) = b
            c + e to d + f
        }.let { it.first - it.second }

    override fun adaptInput(input: String) = input.split("\n")
}
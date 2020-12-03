package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day

class Day5 : Day<List<String>>(5) {
    override fun partOne(input: List<String>) = input.count(this::matchFirstRules)
    override fun partTwo(input: List<String>) = input.count(this::matchSecondRules)

    private val invalidPairs = "ab|cd|pq|xy".toRegex()
    private val letterPairs = "(\\w)\\1".toRegex()
    private val vowels = "[aeiou]".toRegex()
    private fun matchFirstRules(input: String): Boolean {
        if (invalidPairs.containsMatchIn(input)) return false
        if (!letterPairs.containsMatchIn(input)) return false
        if (vowels.findAll(input).count() < 3) return false
        return true
    }

    private val interruptPair = "(\\w)\\w\\1".toRegex()
    private fun matchSecondRules(input: String): Boolean {
        if (!interruptPair.containsMatchIn(input)) return false
        val chars = input.toCharArray()
        return chars.indices.filter { i ->
            val a = chars.getOrNull(i) ?: return@filter false
            val b = chars.getOrNull(i + 1) ?: return@filter false
            return@filter Regex("$a$b").findAll(input).count() >= 2
        }.isNotEmpty()
    }

    override fun adaptInput(input: String) = input.split("\n")
}
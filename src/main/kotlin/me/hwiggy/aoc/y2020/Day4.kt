package me.hwiggy.aoc.y2020

import me.hwiggy.aoc.types.Day

class Day4 : Day<List<Day4.Passport>>(4) {
    override fun partOne(input: List<Passport>) = input.count(Passport::validPartOne)
    override fun partTwo(input: List<Passport>) = input.count(Passport::validPartTwo)

    override fun adaptInput(input: String) = input.split("\n\n").map {
        val values = HashMap<String, String?>().withDefault { null }
        it.split("\\s".toRegex()).map {
            val (key, value) = it.split(":")
            values.put(key, value)
        }
        Passport(values)
    }

    class Passport(map: Map<String, String?>) {
        private val byr = Field(map["byr"]) { it.toInt() in 1920..2002 }
        private val iyr = Field(map["iyr"]) { it.toInt() in 2010..2020 }
        private val eyr = Field(map["eyr"]) { it.toInt() in 2020..2030 }
        private val hgtRegex = Regex("(\\d+)(in|cm)")
        private val hgt = Field(map["hgt"]) {
            val (height, unit) = hgtRegex.matchEntire(it)?.destructured ?: return@Field false
            when (unit) {
                "in" -> height.toInt() in 59..76
                "cm" -> height.toInt() in 150..193
                else -> false
            }
        }
        private val hclRegex = Regex("#[0-9a-f]{6}")
        private val hcl = Field(map["hcl"]) { it.matches(hclRegex) }
        private val eclRegex = Regex("amb|blu|brn|gry|grn|hzl|oth")
        private val ecl = Field(map["ecl"]) { it.matches(eclRegex) }
        private val pidRegex = Regex("\\d{9}")
        private val pid = Field(map["pid"]) { it.matches(pidRegex) }

        private val allFields = listOf(byr, iyr, eyr, hgt, hcl, ecl, pid)

        fun validPartOne() = allFields.all(Field::present)
        fun validPartTwo() = allFields.all(Field::valid)

        private inner class Field(val value: String?, val validator: (String) -> Boolean) {
            fun present() = value != null
            fun valid() = value?.let(validator) ?: false
        }
    }
}

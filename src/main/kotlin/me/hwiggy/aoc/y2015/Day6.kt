package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day
import kotlin.math.max

class Day6 : Day<List<Day6.Instruction>>(6) {
    override fun partOne(input: List<Instruction>): Any {
        val states = HashMap<Position, Boolean>()
        input.forEach { instruction ->
            instruction.positions.forEach {
                when (instruction.action) {
                    "toggle" -> states.compute(it) { _, v -> if (v == null) true else !v }
                    "turn off" -> states.compute(it) { _, _ -> false }
                    "turn on" -> states.compute(it) { _, _ -> true }
                }
            }
        }
        return states.count { it.value }
    }

    override fun partTwo(input: List<Instruction>): Any {
        val states = HashMap<Position, Int>()
        input.forEach { instruction ->
            instruction.positions.forEach {
                when (instruction.action) {
                    "toggle" -> states.compute(it) { _, v -> 2 + (v ?: 0) }
                    "turn on" -> states.compute(it) { _, v -> 1 + (v ?: 0) }
                    "turn off" -> states.compute(it) { _, v -> max((v ?: 0) - 1, 0) }
                }
            }
        }
        return states.values.sum()
    }

    private val pattern = "(.+) (\\d+),(\\d+) through (\\d+),(\\d+)".toRegex()
    override fun adaptInput(input: String) = input.split("\n").map {
        val (action, sx, sy, ex, ey) = pattern.matchEntire(it)!!.destructured
        Instruction(action, Position(sx.toInt(), sy.toInt()), Position(ex.toInt(), ey.toInt()))
    }

    data class Position(val x: Int, val y: Int) {
        operator fun rangeTo(other: Position): List<Position> {
            val list = ArrayList<Position>()
            (x..(other.x)).forEach { x ->
                (y..(other.y)).forEach { y ->
                    list += Position(x, y)
                }
            }
            return list
        }
    }
    data class Instruction(val action: String, val start: Position, val end: Position) {
        val positions = start..end
    }
}
package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day

class Day2 : Day<List<Day2.Present>>(2) {
    override fun partOne(input: List<Present>) = input.map { it.surfaceArea + it.slack }.sum()
    override fun partTwo(input: List<Present>) = input.map { it.volume + it.ribbon }.sum()

    private val regex = "(\\d+)x(\\d+)x(\\d+)".toRegex()
    override fun adaptInput(input: String): List<Present> = input.split("\n").map {
        val (l, w, h) = regex.matchEntire(it)?.destructured!!
        Present(l.toInt(), w.toInt(), h.toInt())
    }.toList()

    class Present(l: Int, w: Int, h: Int) {
        private val lw = l*w
        private val wh = w*h
        private val hl = h*l
        val slack = minOf(lw, wh, hl)
        val surfaceArea = 2*lw + 2*wh + 2*hl
        val ribbon = minOf((2*l)+(2*w), (2*w)+(2*h), (2*h)+(2*l))
        val volume = l*w*h
    }
}
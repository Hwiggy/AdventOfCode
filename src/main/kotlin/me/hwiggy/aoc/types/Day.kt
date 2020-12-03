package me.hwiggy.aoc.types

abstract class Day<T>(val dayNum: Int) {
    abstract fun partOne(input: T) : Any
    abstract fun partTwo(input: T) : Any

    abstract fun adaptInput(input: String) : T
}
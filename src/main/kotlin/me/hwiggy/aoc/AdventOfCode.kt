package me.hwiggy.aoc

import me.hwiggy.aoc.y2015.Year2015
import me.hwiggy.aoc.y2020.Year2020
import java.nio.file.Files
import java.nio.file.Path
import java.time.Instant

object AdventOfCode {
    @JvmStatic val INPUT_FOLDER: Path = Path.of("input").also {
        if (!Files.exists(it)) Files.createDirectory(it)
    }

    @JvmStatic fun main(args: Array<String>) {
        if (args.isEmpty()) throw IllegalStateException("AOC token was not passed as program argument!")
        val token = args[0]
        println("Starting the Advent of Code!")
        listOf(
           // Year2015(),
           Year2020()
        ).forEach { bench({ it.evaluate(token) }, { println("\tTotal Time: $it") }) }
    }
}

fun <T> bench(block: () -> T, callback: (String) -> Unit): T {
    val pre = Instant.now().toEpochMilli()
    val result = block()
    val post = Instant.now().toEpochMilli()
    callback("${post - pre}ms")
    return result
}
package me.hwiggy.aoc.types

import me.hwiggy.aoc.AdventOfCode
import me.hwiggy.aoc.bench
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files

abstract class Year(private val yearNum: Int) {
    private val folder = AdventOfCode.INPUT_FOLDER.resolve(yearNum.toString()).also {
        if (!Files.exists(it)) Files.createDirectory(it)
    }
    abstract val days: Collection<Day<out Any>>

    fun evaluate(token: String) {
        println("Year $yearNum:")
        days.forEach { it as Day<Any>
            bench(
                { val dayNum = it.dayNum
                    println("\tDay $dayNum")
                    val read = getInput(dayNum, token).joinToString("\n")
                    val data = bench({ it.adaptInput(read) }, { println("\t\tInput Parse: $it")})
                    bench(
                        { println("\t\tPart 1: ${runCatching { it.partOne(data)}.getOrElse { it.message } }") },
                        { println("\t\t\tTime: $it")}
                    )
                    bench(
                        { println("\t\tPart 2: ${runCatching { it.partTwo(data)}.getOrElse { it.message } }") },
                        { println("\t\t\tTime: $it")}
                    )
                },
                { println("\t\tTotal Time: $it") }
            )
        }
    }

    private val urlFormat = "https://adventofcode.com/$yearNum/day/%d/input"
    private fun getInput(day: Int, token: String): List<String> {
        val file = folder.resolve("$day.txt")
        if (!Files.exists(file)) {
            (URL(urlFormat.format(day)).openConnection() as HttpURLConnection).also {
                it.setRequestProperty("Cookie", "session=$token")
                it.doInput = true
                it.connect()
                it.inputStream.use { Files.copy(it, file) }
                it.disconnect()
            }
        }
        return Files.readAllLines(file)
    }
}

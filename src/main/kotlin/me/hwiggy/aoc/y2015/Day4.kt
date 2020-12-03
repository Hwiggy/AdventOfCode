package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day
import java.lang.IllegalStateException
import java.math.BigInteger
import java.security.MessageDigest

class Day4 : Day<String>(4) {
    companion object {
        @JvmStatic val MD5 = MessageDigest.getInstance("MD5")
    }

    override fun partOne(input: String): Any {
        (0..Int.MAX_VALUE).forEach {
            val hash = computeMD5("$input$it")
            if (hash.startsWith("00000")) return it
        }
        throw IllegalStateException("Could not find valid hash!")
    }

    override fun partTwo(input: String): Any {
        (0..Int.MAX_VALUE).forEach {
            val hash = computeMD5("$input$it")
            if (hash.startsWith("000000")) return it
        }
        throw IllegalStateException("Could not find valid hash!")
    }


    private fun computeMD5(input: String) = "%032X".format(BigInteger(1, MD5.digest(input.toByteArray())))

    override fun adaptInput(input: String) = input
}
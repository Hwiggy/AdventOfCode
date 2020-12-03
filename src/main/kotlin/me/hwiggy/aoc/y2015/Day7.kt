package me.hwiggy.aoc.y2015

import me.hwiggy.aoc.types.Day

class Day7 : Day<Unit>(7) {
    private val circuit = HashMap<String, Operation>()
    private val cache = HashMap<String, Int>()

    override fun partOne(input: Unit) = circuit["a"]!!.perform()
    override fun partTwo(input: Unit): Any {
        circuit["b"] = literal(partOne(input))
        cache.clear()
        return circuit["a"]!!.perform()
    }

    private val pattern = "(?:(?:(\\w+) )?(LSHIFT|RSHIFT|AND|NOT|OR) )?(\\w+) -> (\\w+)".toRegex()
    override fun adaptInput(input: String) {
        input.split("\n").sorted().associateTo(circuit) {
            val (left, operator, right, dest) = pattern.matchEntire(it)!!.destructured
            val operation: Operation = when (operator) {
                "LSHIFT" -> shl(getOperation(left), getOperation(right))
                "RSHIFT" -> shr(getOperation(left), getOperation(right))
                "AND"    -> and(getOperation(left), getOperation(right))
                "NOT"    -> not(                    getOperation(right))
                "OR"     ->  or(getOperation(left), getOperation(right))
                ""       ->                         getOperation(right)
                else     -> throw IllegalArgumentException("Unmatched operator: $operator")
            }
            dest to operation
        }
    }

    private fun getOperation(identifier: String) =
        identifier.toIntOrNull()?.let(this::literal) ?: query(identifier)

    fun interface Operation { fun perform(): Int }
    private infix fun Operation.shl(other: Operation) = perform() shl other.perform()
    private infix fun Operation.shr(other: Operation) = perform() shr other.perform()
    private infix fun Operation.and(other: Operation) = perform() and other.perform()
    private infix fun Operation.or (other: Operation) = perform()  or other.perform()

    private fun literal(value: Int) = Operation { value }
    private fun query(wire: String) = Operation {
        cache[wire] ?: circuit[wire]!!.perform().also { cache[wire] = it }
    }
    private fun shl(left: Operation, right: Operation) = Operation { left shl right }
    private fun shr(left: Operation, right: Operation) = Operation { left shr right }
    private fun and(left: Operation, right: Operation) = Operation { left and right }
    private fun  or(left: Operation, right: Operation) = Operation { left  or right }
    private fun not(targetOp: Operation) = Operation { targetOp.perform().inv() }
}
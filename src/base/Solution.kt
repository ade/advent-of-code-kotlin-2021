package base

abstract class Solution(private val challenge: Challenge) {
    abstract operator fun invoke()

    protected val input
        get() = challenge.fileAsList("input")

    protected val inputAsInts
        get() = input.map(String::toInt)

    protected val inputAsBinaryInts
        get() = input.map { it.toInt(2) }

    protected val example
        get() = challenge.fileAsList("example")

    protected val exampleAsInts
        get() = example.map(String::toInt)

    protected val exampleAsBinaryInts
        get() = example.map { it.toInt(2) }

}
package base

abstract class Solution(private val challenge: Challenge) {
    abstract operator fun invoke()

    protected val input
        get() = challenge.fileAsList("input")

    protected val inputAsInts
        get() = input.map(String::toInt)

    protected val example
        get() = challenge.fileAsList("example")

    protected val exampleAsInts
        get() = example.map(String::toInt)

}
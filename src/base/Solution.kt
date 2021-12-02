package base

abstract class Solution(val challenge: Challenge) {
    abstract operator fun invoke()

    protected fun readExampleAsList() = challenge.readExampleAsList()
    protected fun readInputAsList() = challenge.readInputAsList()
}
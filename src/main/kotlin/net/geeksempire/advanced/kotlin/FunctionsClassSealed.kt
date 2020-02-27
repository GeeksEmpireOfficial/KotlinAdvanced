package net.geeksempire.advanced.kotlin

sealed class Operations {
    class Press(val data: NumberDataClass) : Operations()
    class PressHold(val data: NumberDataClass) : Operations()

    object PressAction : Operations()
    object PressHoldAction : Operations()
}
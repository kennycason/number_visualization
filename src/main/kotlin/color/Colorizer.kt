package color

import java.awt.Color


interface Colorizer {
    fun apply(i: Int): Color
}
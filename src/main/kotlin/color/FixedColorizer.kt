package color

import java.awt.Color


class FixedColorizer(private val r: Int,
                     private val g: Int,
                     private val b: Int) : Colorizer {

    private val color = Color(r, g, b)

    override fun apply(i: Int) = color

}
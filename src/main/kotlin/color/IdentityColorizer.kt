package color

import java.awt.Color


class IdentityColorizer : Colorizer {
    override fun apply(i: Int): Color {
        val b = i and 0xff
        val g = (i shr 8) and 0xff
        val r = (i shr 16) and 0xff
        return Color(r, g, b)
    }
}
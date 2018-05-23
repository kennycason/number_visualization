package visualize

import org.apache.commons.io.IOUtils
import java.math.BigInteger
import Space2d

class Mod4MSBVisualizer(width: Int,
                        height: Int,
                        resource: String,
                        private var cursor: Point = Point((width / 10) * 5, (height / 10) * 5)) : Visualizer(width, height) {
    private val numberString = NumberLoader.load(resource)
    private var number = BigInteger(numberString)

    override fun visualize(): Space2d {
        val space = Space2d(width, height)
        println(number)
        println(numberString)
        var i = 0
        val totalBits = number.bitLength() / 2
        var j = totalBits
        while (j >= 2) {
            val last4Bits = if (number.testBit(j)) { 1 } else { 0 } + if (number.testBit(j - 1)) { 2 } else { 0 }
            place(i, totalBits, last4Bits, space)
            j -= 2
            i++
        }
        return space
    }

    private fun place(i: Int, totalBits: Int, last4Bits: Int, space: Space2d) {
        when (last4Bits) {
            0 -> { cursor.x++ }
            1 -> { cursor.x-- }
            2 -> { cursor.y++ }
            3 -> { cursor.y-- }
        }
        val color = ((i.toFloat() / totalBits.toFloat()) * 0xFFFFFF).toInt()
        space.set(cursor.x, cursor.y, color)
    }
}
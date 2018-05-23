import java.awt.Color
import java.util.*

class Space2d(val width: Int,
              val height: Int) {
    private val grid: Array<Array<Int>> = array2d(width, height, { 0 } )

    fun get(x: Int, y: Int) = grid[(x + width) % width][(y + height) % height]
    fun set(x: Int, y: Int, v: Int) {
        // println("${Math.floorMod(x, width)}, y =${Math.floorMod(y, height)}")
        grid[Math.floorMod(x, width)][Math.floorMod(y, height)] = v
    }

    override fun toString(): String {
        val sb = StringBuilder()
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                sb.append(get(x, y))
                sb.append(',')
            }
            sb.append('\n')
        }
        return sb.toString()
    }
}
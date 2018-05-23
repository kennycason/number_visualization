package visualize
import org.apache.commons.io.IOUtils
import Space2d

class Base4Mod4Visualizer(width: Int,
                          height: Int,
                          resource: String,
                          private var cursor: Point = Point((width / 10) * 5, (height / 10) * 5)) : Visualizer(width, height) {
    private val numberString = NumberLoader.load(resource)

    override fun visualize(): Space2d {
        val space = Space2d(width, height)
        for (i in (0 until numberString.length)) {
            place(i, space)
        }
        return space
    }

    private fun place(i: Int, space: Space2d) {
        val digit = Integer.parseInt(numberString[i].toString())
        when (digit) {
            0 -> { cursor.x++ }
            1 -> { cursor.x-- }
            2 -> { cursor.y++ }
            3 -> { cursor.y-- }
        }
        val color = ((i.toFloat() / numberString.length) * 0xFFFFFF).toInt()
        space.set(cursor.x, cursor.y, color)
    }
}
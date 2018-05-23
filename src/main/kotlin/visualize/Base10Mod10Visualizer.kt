package visualize
import org.apache.commons.io.IOUtils
import Space2d

class Base10Mod10Visualizer(width: Int, height: Int, resource: String) : Visualizer(width, height) {
    val numberString = IOUtils
            .toString(Thread.currentThread().contextClassLoader.getResourceAsStream(resource))
            .replace("\n".toRegex(), "")
    var cursor = Point((width / 4) * 2, (height / 5) * 3)

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
            0 -> {}
            1 -> { cursor.x++ }
            2 -> { cursor.x-- }
            3 -> { cursor.y++ }
            4 -> { cursor.y-- }
            5 -> { cursor.x++; cursor.y++ }
            6 -> { cursor.x--; cursor.y++ }
            7 -> { cursor.y--; cursor.x++ }
            8 -> { cursor.y--; cursor.x-- }
            9 -> {}
        }
        val color = ((i.toFloat() / numberString.length) * 0xFFFFFF).toInt()
        space.set(cursor.x, cursor.y, color)
    }
}
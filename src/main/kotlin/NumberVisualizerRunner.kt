import visualize.*
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO


fun main(args: Array<String>) {
    NumberVisualizer().run()
}

class NumberVisualizer {
    val random = Random()
    val imageWidth = 1250
    val imageHeight = 1250
    val cellDim = 1
    val width = imageWidth / cellDim
    val height = imageHeight / cellDim

    fun run() {
        // val visualizer: Visualizer = Base10Mod10Visualizer(width, height, "mersenne35.txt")
        val visualizer: Visualizer = Mod4LSBVisualizer(width, height, "e_digits.txt")
        //val visualizer: Visualizer = Base4Mod4Visualizer(width, height, "base4_random.txt")

        val space = visualizer.visualize()
        // ColorFiller().fill(space)
        ImageIO.write(draw(space), "png", File("/tmp/numbers_${System.currentTimeMillis()}.png"))
    }

    private fun draw(space: Space2d): BufferedImage {
        var canvas = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB)
        var canvasGraphic = canvas.createGraphics()

        val freq = 1.0 / 1_000_000.0
        (0 until width).forEach { x ->
            (0 until height).forEach { y ->
                canvasGraphic.color = if (space.get(x, y) == 0) {
                    Color.BLACK
                } else {
                    toColor(space.get(x, y),
                        f1 = freq,
                        f2 = freq,
                        f3 = freq,
                        center = 200,
                        width = 55)
                }
                canvasGraphic.fillRect(x * cellDim, y * cellDim, cellDim, cellDim)
            }
        }
        return canvas
    }

    private fun toColor2(c: Int): Color {
        val b = c and 0xff
        val g = (c shr 8) and 0xff
        val r = (c shr 16) and 0xff
        return Color(r, g, b)
    }

    private fun toColor(i: Int,
                         f1: Double = 0.3,
                         f2: Double = 0.3,
                         f3: Double = 0.3,
                         p1: Double = 0.0,
                         p2: Double = 2.0,
                         p3: Double = 4.0,
                         center: Int = 128,
                         width: Int = 127): Color {
        val r = Math.sin(f1 * i + p1) * width + center
        val g = Math.sin(f2 * i + p2) * width + center
        val b = Math.sin(f3 * i + p3) * width + center

        return Color(r.toInt(), g.toInt(), b.toInt())
    }
}

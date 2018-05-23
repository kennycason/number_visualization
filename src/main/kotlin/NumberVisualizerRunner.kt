import color.SmoothColorizer
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
    val imageWidth = 1000
    val imageHeight = 1000
    val cellDim = 1
    val width = imageWidth / cellDim
    val height = imageHeight / cellDim

    fun run() {
        // val visualizer: Visualizer = Base10Mod10Visualizer(width, height, "mersenne35.txt")
        val visualizer: Visualizer = Mod4LSBVisualizer(width, height, "pi_digits.txt", cursor = Visualizer.Point(width / 4 * 3, height / 4))
        //val visualizer: Visualizer = Base4Mod4Visualizer(width, height, "base4_random.txt")

        val space = visualizer.visualize()
        //ColorFiller().fill(space)
        ImageIO.write(draw(space), "png", File("/tmp/numbers_${System.currentTimeMillis()}.png"))
    }

    private fun draw(space: Space2d): BufferedImage {
        val canvas = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB)
        val canvasGraphic = canvas.createGraphics()

        val freq = 1.0 / 1_000_000.0
        val colorizer = SmoothColorizer(
                f1 = freq,
                f2 = freq,
                f3 = freq,
                center = 200,
                width = 55
        )

        (0 until width).forEach { x ->
            (0 until height).forEach { y ->
                canvasGraphic.color = if (space.get(x, y) == 0) {
                    Color.BLACK
                } else {
                    colorizer.apply(space.get(x, y))
                }
                canvasGraphic.fillRect(x * cellDim, y * cellDim, cellDim, cellDim)
            }
        }
        return canvas
    }

}

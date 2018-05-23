package visualize

import Space2d

class ColorSpectrumVisualizer(width: Int, height: Int) : Visualizer(width, height) {

    override fun visualize(): Space2d {
        val space = Space2d(width, height)
        var i = 0
        (0 until space.height).forEach { y ->
            (0 until space.width).forEach { x ->
                space.set(x, y, i++)
            }
        }
        return space
    }

}
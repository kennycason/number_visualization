package visualize
import Space2d

abstract class Visualizer(val width: Int, val height: Int) {
    data class Point(var x: Int, var y: Int)
    abstract fun visualize(): Space2d
}
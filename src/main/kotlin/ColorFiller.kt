import java.awt.Color

// fill in black/empty portions of space with decreasing rgb value of neighboring cell
class ColorFiller {
    data class Point(val x: Int, val y: Int)

    fun fill(space: Space2d) {
        val next = mutableSetOf<Point>()
        do {
            next.clear()
            (0 until space.width).forEach { x ->
                (0 until space.height).forEach { y ->
                    if (space.get(x, y) == 0) { return@forEach }

                    // only place squares with a black (empty) border
                    if (space.get(x - 1, y) == 0
                            || space.get(x + 1, y) == 0
                            || space.get(x, y - 1) == 0
                            || space.get(x, y + 1) == 0) {
                        next.add(Point(x, y))
                    }
                }
            }

            // fill in neighbors
            next.forEach { n ->
                if (space.get(n.x - 1, n.y) == 0) {
                    space.set(n.x - 1, n.y, space.get(n.x, n.y) - 1)
                }
                if (space.get(n.x + 1, n.y) == 0) {
                    space.set(n.x + 1, n.y, space.get(n.x, n.y) - 1)
                }
                if (space.get(n.x, n.y - 1) == 0) {
                    space.set(n.x, n.y - 1, space.get(n.x, n.y) - 1)
                }
                if (space.get(n.x, n.y + 1) == 0) {
                    space.set(n.x, n.y + 1, space.get(n.x, n.y) - 1)
                }
            }

        } while (!next.isEmpty())
    }
}
import java.util.*

fun main(args: Array<String>) {
    randomBase4Number()
}

fun randomBase4Number() {
    val random = Random()
    (0 until 100_000).forEach {
        print(random.nextInt(4))
    }
}
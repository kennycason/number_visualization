import org.apache.commons.io.IOUtils

object NumberLoader {
    fun load(resource: String) = IOUtils
            .toString(Thread.currentThread().contextClassLoader.getResourceAsStream(resource))
            .replace("\n".toRegex(), "")
}
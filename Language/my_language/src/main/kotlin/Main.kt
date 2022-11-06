import java.io.File

fun main() {
    val scanner = Scanner()
    try {
        scanner.scanProgram(File("D:\\fac\\Anul 3 sem 1\\FLCD_Ianis_Teja\\Language\\my_language\\src\\main\\resources\\p1err.txt"))
    } catch (e: Exception) {
        println("Exception: $e")
    } finally {
        println(scanner.toString())
    }
}
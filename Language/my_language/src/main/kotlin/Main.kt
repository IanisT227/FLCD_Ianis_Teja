import java.io.File

/**
 * Statement: Implement a scanner (lexical analyzer):
 * Implement the scanning algorithm and use ST from lab 2 for the symbol table.
 */

fun main() {
    val scanner = Scanner()
    scanner.scanProgram(File("C:\\Users\\Ianis Teja\\Desktop\\Fac\\anul 3 sem 1\\LFTC\\FLCD_Ianis_Teja\\Language\\my_language\\src\\main\\resources\\p1.txt"))
    println(scanner.toString())

}
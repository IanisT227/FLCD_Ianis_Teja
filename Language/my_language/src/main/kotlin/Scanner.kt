import java.io.File

class Scanner {
    private val identifiers = SymbolTable()
    private val constants = SymbolTable()
    private val pif = ArrayList<Pair<String, Pair<Int, Int>>>()

    private fun addInPif(value: String) {
        if (isToken(value)) {
            pif.add(Pair(value, Pair(-1, -1)))
            return
        }

        if (isConstant(value)) {
            constants.addIdentifier(value)
            pif.add(Pair(value, constants.getIdentifier(value) ?: Pair(-1, -1)))
            return
        }

        if (isIdentifier(value)) {
            identifiers.addIdentifier(value)
            pif.add(Pair(value, identifiers.getIdentifier(value) ?: Pair(-1, -1)))
            return
        }

        println("Error at $value")
    }

    fun scanProgram(file: File) {
        file.useLines { lines ->
            lines.forEach {
                val words = it.split("[\\s.;:,()\\[\\]{}<>]".toRegex())

                println(words)
                words.forEach {
                    addInPif(it)
                }
            }
        }
    }

    private fun isToken(toCheck: String): Boolean {
        File("C:\\Users\\Ianis Teja\\Desktop\\Fac\\anul 3 sem 1\\LFTC\\FLCD_Ianis_Teja\\Language\\my_language\\src\\main\\resources\\Tokens.in").useLines { lines ->
            lines.forEach {
                if (it == toCheck)
                    return true
            }
        }
        return false
    }

    private fun isConstant(toCheck: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return toCheck.matches(regex)
    }

    private fun isIdentifier(toCheck: String): Boolean {
        val regex = "[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()
        return !toCheck.contains(regex)
    }

    override fun toString(): String {
        return "Scanner(identifiers=$identifiers\n constants=$constants\n pif=$pif)"
    }
}
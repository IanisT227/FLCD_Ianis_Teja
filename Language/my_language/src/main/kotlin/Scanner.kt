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
            constants.addValue(value)
            pif.add(Pair(value, constants.getValue(value) ?: Pair(-1, -1)))
            return
        }

        if (isIdentifier(value)) {
            identifiers.addValue(value)
            pif.add(Pair(value, identifiers.getValue(value) ?: Pair(-1, -1)))
            return
        }
        println("Error at $value")
    }

    fun scanProgram(file: File) {
        file.useLines { lines ->
            lines.forEach {
                val words = it.split(
                    SEPARATORS_REGEX
                )
                println(words)
                words.forEach {
                    addInPif(it)
                }
            }
        }
    }

    private fun isToken(toCheck: String): Boolean {
        File("D:\\fac\\Anul 3 sem 1\\FLCD_Ianis_Teja\\Language\\my_language\\src\\main\\resources\\Tokens.in").useLines { lines ->
            lines.forEach {
                if (it == toCheck)
                    return true
            }
        }
        return false
    }

    private fun isConstant(toCheck: String): Boolean {
        return toCheck.matches(NUMBER_REGEX) || toCheck.matches(STRING_REGEX) || toCheck.matches(STRING_REGEX_QUOTES)
    }

    private fun isIdentifier(toCheck: String): Boolean {
        val regex = "[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()
        return !toCheck.contains(regex)
    }

    override fun toString(): String {
        return "Scanner(identifiers=$identifiers\n constants=$constants\n pif=$pif)"
    }

    companion object {
        private const val SEPARATORS = "\\s|.|,|;|:|\\(|\\)|\\[|\\]|\\{|\\}|<|>|="
        private val SEPARATORS_REGEX = "((?=[$SEPARATORS])|(?<=[$SEPARATORS]))".toRegex()
        private val STRING_REGEX = "\"[a-zA-Z0-9]+\"".toRegex()
        private val STRING_REGEX_QUOTES = "“[a-zA-Z0-9]+“".toRegex()
        private val NUMBER_REGEX = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    }
}
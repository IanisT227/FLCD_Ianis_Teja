fun main() {
    val symbolTable = SymbolTable(50)
    println(symbolTable.addIdentifier("heyy"))
    println(symbolTable.addIdentifier("goobye"))
    println(symbolTable.addIdentifier("23"))
    println(symbolTable.addIdentifier("4"))
    println(symbolTable.addIdentifier("32"))
    println("\n")
    println(symbolTable.getIdentifier("heyy"))
    println(symbolTable.toString())
}
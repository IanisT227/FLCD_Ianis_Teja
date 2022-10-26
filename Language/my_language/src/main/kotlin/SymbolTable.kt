class SymbolTable(val size: Int = 50) {
    private val hashTable = HashTable<String>(size)

    fun addIdentifier(idName: String) = hashTable.addItem(idName)

    fun getIdentifier(idName: String) = hashTable.getItem(idName)

    override fun toString(): String {
        return "SymbolTable(size=$size, identifierHashTable=$hashTable)"
    }
}
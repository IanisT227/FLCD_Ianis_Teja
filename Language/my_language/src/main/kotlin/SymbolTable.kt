class SymbolTable(val size: Int = 50) {
    private val identifierHashTable = HashTable<String>(50)
    private val constantHashTable = HashTable<String>(size)

    fun addIdentifier(idName: String) = identifierHashTable.addItem(idName)

    fun addConstant(stringConstant: String) = constantHashTable.addItem(stringConstant)

    fun getIdentifier(idName: String) = identifierHashTable.getItem(idName)

    fun getConstant(stringConstant: String) = constantHashTable.getItem(stringConstant)

    override fun toString(): String {
        return "SymbolTable(size=$size, identifierHashTable=$identifierHashTable, constantHashTable=$constantHashTable)"
    }

//a single hashTable implementation
}
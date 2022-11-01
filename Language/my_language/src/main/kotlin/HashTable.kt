import kotlin.math.abs

class HashTable<T>(private val size: Int) {

    private val hashTable: Array<MutableList<T>> = Array(size) { mutableListOf() }

    private fun hashItem(elem: T): Int {
        if (elem is Int)
            return abs(elem) % size
        if (elem is String) {
            var sum = 0
            for (char in elem)
                sum += char.code.toByte().toInt()
            return abs(sum) % size
        } else
            return -1
    }

    fun getItem(elem: T): Pair<Int, Int>? {
        val posInHashTable = hashItem(elem)
        for ((posInList, elementInHashTable) in hashTable[posInHashTable].withIndex()) {
            if (elementInHashTable == elem)
                return Pair(posInHashTable, posInList)
        }
        return null
    }

    fun addItem(elem: T): Pair<Int, Int> {
        if (getItem(elem) != null)
            return getItem(elem)!!
        val posInHashTable = hashItem(elem)
        val posInRow = hashTable[posInHashTable].size
        hashTable[posInHashTable].add(elem)
        return Pair(posInHashTable, posInRow)
    }

    override fun toString(): String {
        var toString = ""
        hashTable.forEach {
            toString = toString + it.toString() + "\n"
        }
        return "HashTable(hashTable=${toString})"
    }
}
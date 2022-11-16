import java.io.File

class FiniteAutomata(private val file: String) {

    private val allStates: MutableList<String> = mutableListOf()
    private val inputSymbols: MutableList<String> = mutableListOf()
    private var initialState: String = ""
    private val finalStates: MutableList<String> = mutableListOf()
    private val transitionFunctions: MutableList<Pair<Pair<String, String>, String>> = mutableListOf()

    private fun readLines() {
        for (line in File(file).readLines()) {
            when ("^[a-z_?]+=".toRegex().find(line)?.groups?.get(0)?.value) {
                "states=" -> {
                    val allStatesString = line.substringAfter("=").trim()
                    allStatesString.split(",").forEach {
                        allStates.add(it)
                    }
                }
                "input_symbols=" -> {
                    val allInputSymbols = line.substringAfter("=").trim()
                    allInputSymbols.split(",").forEach {
                        inputSymbols.add(it)
                    }
                }
                "initial_state=" -> {
                    val state = line.substringAfter("=").trim()
                    if (state !in allStates) {
                        println("Initial state is not in all states")
                    } else {
                        initialState = state
                    }
                }
                "final_states=" -> {
                    val allFinalStates = line.substringAfter("=").trim()
                    allFinalStates.split(",").forEach {
                        if (it in allStates) {
                            finalStates.add(it)
                        } else
                            println("the symbol $it cannot be found in the list of all states")

                    }

                }
                "transition_functions=" -> {
                    val allTransitionFunctions = line.substringAfter("=").trim()
                    allTransitionFunctions.split(";").forEach {
                        val transitionFunctionFormatted = it.slice(1 until it.length - 1).trim()
                        val transitionItems = transitionFunctionFormatted.split(", *".toRegex())
                        transitionFunctions.add(Pair(Pair(transitionItems[0], transitionItems[1]), transitionItems[2]))
                    }
                }
                else -> {
                    println("Invalid line!")
                }
            }
        }
    }

    private fun checkSequence(sequence: String): Boolean {
        var currentState = initialState
        for (item in sequence) {
            var found = false
            for (transition in transitionFunctions) {
                if (transition.first.first == currentState && transition.first.second == item.toString()) {
                    currentState = transition.second
                    found = true
                    break
                }
            }
            if (!found)
                return false
        }
        return currentState in finalStates
    }

    fun showMenu() {
        readLines()
        println(
            "Hi!\n" +
                    "Press 1 to see the Finite Automata\n" +
                    "Press 2 to write a sequence and check it\n" +
                    "Press 0 to exit\n"
        )
        while (true) {
            println("\nYour input:")
            when (readLine()!!.trim()) {
                "1" -> {
                    println(toString())
                }
                "2" -> {
                    println("Your sequence: ")
                    val sequence = readLine()!!.trim()
                    if (checkSequence(sequence))
                        println("Your sequence is correct!")
                    else
                        println("Your sequence is incorrect!")
                }
                "0" -> {
                    println("Thanks for using the app! :)")
                    break
                }
                else -> {
                    println("Invalid Input!")
                }
            }
        }
    }

    override fun toString(): String {
        return "FiniteAutomata(\n\tallStates=$allStates,\n\tinputSymbols=$inputSymbols,\n\tinitialState='$initialState',\n\tfinalStates=$finalStates,\n\ttransitionFunctions=$transitionFunctions)"
    }
}
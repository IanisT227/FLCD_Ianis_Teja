import java.io.File

class FiniteAutomata(val file: String) {

    private val allStates: MutableList<String> = mutableListOf()
    private val inputSymbols: MutableList<String> = mutableListOf()
    private var initialState: String = ""
    private val finalStates: MutableList<String> = mutableListOf()
    private val transitionFunctions: MutableList<Pair<Pair<String, String>, String>> = mutableListOf()

    fun readLines() {
        for (line in File(file).readLines()) {
            when ("^[a-z_?]+=".toRegex().find(line)?.groups?.get(0)?.value) {
                "states=" -> {
                    val allStatesString = line.substringAfter("=").trim()
                    allStatesString.split(",").forEach {
                        allStates.add(it)
                    }
                    println(allStates)
                    println(allStates[0])

                }
                "input_symbols=" -> {
                    val allInputSymbols = line.substringAfter("=").trim()
                    allInputSymbols.split(",").forEach {
                        inputSymbols.add(it)
                    }
                    println(inputSymbols)
                }
                "initial_state=" -> {
                    val state = line.substringAfter("=").trim()
                    if (state !in allStates) {
                        println("Initial state is not in all states")
                    } else {
                        initialState = state
                    }

                    println("Initial State="+initialState)
                }
                "final_states=" -> {
                    val allFinalStates = line.substringAfter("=").trim()
                    allFinalStates.split(",").forEach {
                        finalStates.add(it)
                    }

                }
                "transition_functions=" -> {
                    val allTransitionFunctions = line.substringAfter("=").trim()
                    println(allTransitionFunctions)
                    allTransitionFunctions.split(";").forEach {
                        println(it)
                    }
                }
            }
        }
    }
}
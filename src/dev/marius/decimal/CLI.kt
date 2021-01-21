package dev.marius.decimal

import java.lang.NumberFormatException
import java.util.*

// TODO: [binary,hexadecimal,octal] --> decimal
// TODO: Add language objects for that
class CLI {

    // Init the calculator
    private val calculator: Calculator = Calculator()

    fun invoke() {
        // Init vars
        val input = Scanner(System.`in`)

        // Print Menu
        println("========== ${Language.get("cli-header")} ==========")
        println("1) ${Language.get("cli-toBin")}")
        println("2) ${Language.get("cli-toOct")}")
        println("3) ${Language.get("cli-toHex")}")
        println("4) ${Language.get("cli-toAll")}")
        println("")
        println(Language.get("cli-exit", "exit"))

        // Program loop
        var run = true
        while (run) {
            // Get input from command line
            print("> ")
            val inp = input.next()
            // Check if input is empty
            if (inp.isBlank() || inp.isEmpty()) return
            // Check which argument is provided
            when (inp) {
                // Binary
                "1" -> {
                    // Print Header
                    println("===> ${Language.get("cli-toBin")}")
                    // Get input from command line
                    val dec = decInput(input)

                    // Calculate
                    val result = calculator.translateFromDecimal(dec, 2)
                    // Catch errors
                    if (result.getString("code") == "error") {
                        println("Error: ${result.getString("message")}")
                    } else {
                        // Print result
                        println(Language.get("cli-is", dec, "binary", result.getString("message")))
                    }
                }
                // Octal
                "2" -> {
                    // Print Header
                    println("===> ${Language.get("cli-toOct")}")
                    // Get input from command line
                    val dec = decInput(input)

                    // Calculate
                    val result = calculator.translateFromDecimal(dec, 8)
                    // Catch errors
                    if (result.getString("code") == "error") {
                        println("Error: ${result.getString("message")}")
                    } else {
                        // Print result
                        println(Language.get("cli-is", dec, "octal", result.getString("message")))
                    }
                }
                // Hexadecimal
                "3" -> {
                    // Print Header
                    println("===> ${Language.get("cli-toHex")}")
                    // Get input from command line
                    val dec = decInput(input)

                    // Calculate
                    val result = calculator.translateFromDecimal(dec, 16)
                    // Catch errors
                    if (result.getString("code") == "error") {
                        println("Error: ${result.getString("message")}")
                    } else {
                        // Print result
                        println(Language.get("cli-is", dec, "hexadecimal", result.getString("message")))
                    }
                }
                // All
                "4" -> {
                    // Print header
                    println("===> ${Language.get("cli-toAll")}")
                    // Get input from command line
                    val dec = decInput(input)

                    // Calculate
                    val result = calculator.translateFromDecimal(dec, 2)
                    val result1 = calculator.translateFromDecimal(dec, 8)
                    val result2 = calculator.translateFromDecimal(dec, 16)

                    // Catch errors
                    when {
                        result.getString("code") == "error" -> {
                            println("Error: ${result.getString("message")}")
                        }
                        result1.getString("code") == "error" -> {
                            println("Error: ${result.getString("message")}")
                        }
                        result2.getString("code") == "error" -> {
                            println("Error: ${result.getString("message")}")
                        }
                        else -> {
                            // Print results
                            println(Language.get("cli-is", dec, "binary", result.getString("message")))
                            println(Language.get("cli-is", dec, "octal", result1.getString("message")))
                            println(Language.get("cli-is", dec, "hexadecimal", result2.getString("message")))
                        }
                    }
                }
                // Exit operations
                "exit", "close", "quit", "q" -> run = false // End while-loop
                // Catch all other commands
                else -> {
                    println("Error: ${Language.get("cli-unknownCommand")}")
                }
            }
        }
    }

    private fun decInput(`in`: Scanner) : Int {
        // Print question
        print("${Language.get("cli-question")} : ")
        // Get user input
        val got = `in`.next()
        // Check if string is empty, when true return this method again
        if (got.isBlank() || got.isEmpty()) {
            println("Error: ${Language.get("cli-noInput")}")
            return decInput(`in`)
        }
        return try {
            // Parse String to Integer for return
            got.toInt()
        } catch (e: NumberFormatException) {
            // Handling NumberFormatException with calling this method again
            println("Error: ${Language.get("cli-numberFormat")}")
            decInput(`in`)
        }
    }

}
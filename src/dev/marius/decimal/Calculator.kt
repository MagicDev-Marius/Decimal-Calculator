package dev.marius.decimal

import dev.marius.decimal.JavaUtility.*
import org.json.JSONObject
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import kotlin.math.pow

class Calculator {

    private val hexLetterMap = HashMap<Int, Char>()

    init {
        hexLetterMap[10] = 'A'
        hexLetterMap[11] = 'B'
        hexLetterMap[12] = 'C'
        hexLetterMap[13] = 'D'
        hexLetterMap[14] = 'E'
        hexLetterMap[15] = 'F'
    }

    fun translateFromDecimal(decimal: Int, base: Int): JSONObject {
        var temp = decimal
        val out = StringBuilder()
        if (base != 2 && base != 8 && base != 16) throw RuntimeException("This base is not valid for the following systems: Binary, Octal and Hexadecimal")
        try {
            return if (base != 16) {
                while (temp != 0) {
                    val rest = temp % base
                    out.append(rest)
                    temp /= base
                }
                JSONObject("{\"code\":\"success\",\"message\":\"${reverse(out.toString())}\"}")
            } else {
                var integers = ArrayList<String?>()
                while (temp != 0) {
                    val rest = temp % base
                    integers.add(rest.toString())
                    temp /= base
                }
                integers = replace(integers, hexLetterMap)
                for (c in reverse(toString(integers)).toCharArray()) out.append(c)
                JSONObject("{\"code\":\"success\",\"message\":\"$out\"}")
            }
        } catch (e: Exception) {
            return JSONObject("{\"code\":\"error\",\"message\":\"${e.localizedMessage}\"}")
        }
    }

    fun translateToDecimal(input: String, base: Int): JSONObject {
        try {
            val set: MutableSet<Double<Int, Char>> = LinkedHashSet()
            var factor = input.length
            for (c in input.toCharArray()) {
                set.add(Double(base.toDouble().pow(factor.toDouble()).toInt(), c))
                factor--
            }
            val replace: MutableMap<Char, Int> = HashMap()
            replace['0'] = 0
            replace['1'] = 1
            replace['2'] = 2
            replace['3'] = 3
            replace['4'] = 4
            replace['5'] = 5
            replace['6'] = 6
            replace['7'] = 7
            replace['8'] = 8
            replace['9'] = 9
            replace['a'] = 10
            replace['b'] = 11
            replace['c'] = 12
            replace['d'] = 13
            replace['e'] = 14
            replace['f'] = 15
            val allInts: MutableList<Int> = ArrayList()
            set.forEach {
                allInts.add(replace[it.value]!! * it.key)
            }
            val sum = AtomicInteger(0)
            allInts.forEach(Consumer { integer: Int -> sum.set(sum.get() + integer) })
            return JSONObject("{\"code\":\"success\",\"message\":\"${sum.get()/2}\"}")
        } catch (e: Exception) {
            return JSONObject("{\"code\":\"error\",\"message\":\"${e.localizedMessage}\"}")
        }
    }

    class Double<K, V>(val key: K, val value: V)

}
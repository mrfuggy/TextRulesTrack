package parser

import java.io.File

val rules = File("./src/Rules.ini")
        .readLines()
        .asSequence()
        .map { it.split(delimiters = '=', ignoreCase = false, limit = 2) }
        .map { Pair(it[0], Regex(it[1])) }
        .toMap()
//word1 word (word3) 2 : 12 (+12) - 8 - tag1, tag2

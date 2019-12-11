package ca.ciccc.exercises

fun isValidIdentifier(s: String): Boolean {
    if(s =="")
        return false

    if(s in "0".."9")
        return false

    for (ch in s) {
        if (!ch.isLetterOrDigit()) {
            if (ch == '_') {
                continue
            }
            return false
        }
    }
    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}
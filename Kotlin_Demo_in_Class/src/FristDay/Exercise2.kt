package ca.ciccc.exercises

/// Change the 'sum' function so that it was declared as an extension to List<Int>.
//fun sum(list: List<Int>): Int {
//    var result = 0
//    for (i in list) {
//        result += i
//    }
//    return result
//}


fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

fun main() {
//    val sum = sum(listOf(1, 2, 3))
//    println(sum)
    val sum2 = listOf(1, 2, 3, 4, 5, 6, 7).sum()
    println(sum2)
}
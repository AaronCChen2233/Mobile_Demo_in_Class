package FristDay

import kotlin.random.Random

/*Scope Functions in Kotlin
let
with
apply
also
list
run
*/
data class Person(var name: String = "", var age: Int = 0, var city: String = "")

val rick = Person("Rick").apply {
    age = 28
    city = "Vancouver"
}

fun getRandomInt(): Int {
    return Random.nextInt(100).also {
        println("Random number is $it")
        it + 1
    }
}

fun main() {
    val r = getRandomInt()
    println(r)
    "Vancouver".run {
        println("Hello, $this")
    }
    val ages = mutableListOf<Int>()
    ages.also { println("Populating the age list!") }
        .apply {
            add(28)
            add(29)
            add(30)
            add(15)
            add(9)
        }
        .also { println("I will sort the age list") }
        .sort()
    println(ages)

    val numbers = mutableListOf<String>("one", "two", "three")
    var CountE = numbers.run {
        add("four")
        add("five")
        add("six")
        count { it.endsWith("e") }
        /*returns lambda result*/
    }
    println("Count: $CountE")


    val nums = mutableListOf<String>("one", "two", "three")
    with(nums) {
        println("FirstItem: ${first()}, LastItem: ${last()}")
        /*returns lambda result*/
    }

    /*let is often used for executing a nullable code block*/
    val str: String? = "Vancouver"
    val length = str?.let {
        println("let() is called on $it")
        it.length
        /*return lambda result*/
    }
    println(length)

}

/*
* Function  obj ref      returns
* ===================================
* let          it      lambda result
* run         this     lambda result
* run          -       lambda result
* with        this     lambda result
* apply       this     context obj
* also         it      context obj
* */

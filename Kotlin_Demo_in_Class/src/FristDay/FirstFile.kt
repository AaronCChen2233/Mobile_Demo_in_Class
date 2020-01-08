//@file:JvmName("bojlefc")

package FristDay

import java.io.IOException

fun main(args: Array<String>) {
    45 eq 45
    40 eq 41
    "abc" eq "abc"
    "abcde" eq "ab"
    true eq 1
//    println("name".lastChar())
}

/*New ver args is optional*/
//fun FristDay.main(args: Array<String>) {
//    val name = "Kotlin"
//    var greeting = "Hello";
//    println("$greeting, $name")
//    greeting = "Hi"
//    println("$greeting, $name")
//    println(listOf(1, 2, 3, 4).joinToString(postfix = "..."))
//}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

class Mathtest {
    fun min(a: Int, b: Int): Int {
        return if (a < b) a else b
    }
}

fun String.lastChar() = this.get(this.length-1)

fun isValidIdentifier(s: String): Boolean {
    return false
}

@Throws(IOException::class)
fun test()
{
    throw IOException()
}

/**/
//val // Read-only variable same final in jave
//Unit // void in java
//@JvmOverloads
//Extension
//@Throws(IOException::class)

//because java doesn't have Extension so if java call kotlin file should input it self at first arg

//infix

//kotlin default class is final class so should use
//open class parent
//kotlin can't override
//s?.length ?: 0
//Elvis operator
//s!!.length // null exception
//(any as? String)?.toUpperCase()  // if is string return toUpperCase, If not will return null
//val FristDay.getFoo Int get(){
// return 2
// }
//field like set value in C#
//anonymous class : class without a name
//companion object
//it like e => e in C#
//it like e -> e
//map.mapValue {(key,value) -> "$key -> $value"}
//Higher Order Function
//is a function of lambda that takes func as arguments or return func
//EX: list.filter({}).any.map etc
//.select in C# is .map in java or kotlin
//.any in C# and kotlin in java is include
//partition will get two list one is true another is false
//groupBy
//associateBy
//zip
//flat
//flatMap
//f?.invoke()
//if have return in the lambda that return always return out of the function not the lambda
//if want to return the lambda put return@lambdaName ex return@flatMap
//other solution is put anonymous function inside the lambda
//can store lambda in a var but can't store function in a var
//but can reference a function
//var varName =::functionName
//var re = ::isEven
//Inflate
//A idea is per app only have one activity and bunch of Fragments
//Navigation graph
//RecycleView
//ViewHolder When user Scroll the view won't change only change the data
//Adapter
//Those three function should override
//getItemCount()
//onCreateViewHolder()
//onBindViewHolder()
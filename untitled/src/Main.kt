fun buildAquarium() {
    val myAquarium = Aquarium(width = 25, length = 25, height = 40)
    myAquarium.printSize()
    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()
    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${pleco.color}")
    pleco.eat()
}

fun makeDecorations() {
//    val decoration1 = Decoration("granite")
//    println(decoration1)
//
//    val decoration2 = Decoration("slate")
//    println(decoration2)
//
//    val decoration3 = Decoration("slate")
//    println(decoration3)
//
//    println(decoration1.equals(decoration2))
//    println(decoration3.equals(decoration2))
//
//    println(decoration1 == decoration2)
//    println(decoration2 === decoration3)

    val d5 = Decoration("crystal", "wood", "diver")
    println(d5)

// Assign all properties to variables.
    val (rock, wood, diver) = d5
    println(rock)
    println(wood)
    println(diver)
}

fun enumTest(){
    println(Direction.EAST.name)
    println(Direction.EAST.ordinal)
    println(Direction.EAST.degrees)
}

fun main() {
//    makeFish()
//    makeDecorations()
    enumTest()
}
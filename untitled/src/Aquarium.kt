
import java.lang.Math.PI

open class Aquarium(var width: Int = 20, open var height: Int = 40, var length: Int = 100) {

    open var volume: Int
        get() = width * height * length / 1000
        set(value) {
            height = (value * 1000) / (width * length)
        }
    open val shape = "rectangle"
    open var water: Double = 0.0
        get() = volume * 0.9

    fun printSize() {
        println(shape)
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
        // 1 l = 1000 cm^3
        println("Volume: $volume l Water: $water l (${water / volume * 100.0}% full)")
    }
}

class TowerTank(override var height: Int, var diameter: Int) : Aquarium(height = height, width = diameter, length = diameter) {
    override var volume: Int
        // ellipse area = π * r1 * r2
        get() = (width / 2 * length / 2 * height / 1000 * PI).toInt()
        set(value) {
            height = ((value * 1000 / PI) / (width / 2 * length / 2)).toInt()
        }
    override var water = volume * 0.8
    override val shape = "cylinder"
}

interface FishAction {
    fun eat()
}

abstract class AquariumFish : FishAction {
    abstract val color: String
    override fun eat() = println("yum")
}

class Shark : AquariumFish(), FishAction {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

interface FishColor {
    val color: String
}

object GoldColor : FishColor {
    override val color = "gold"
}

class Plecostomus(fishColor: FishColor = GoldColor) :
    FishAction by PrintingFishAction("eat algae")
    , FishColor by fishColor {

}

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}

data class Decoration(val rocks: String, val wood: String, val diver: String) {

}

enum class Color(val rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}

enum class Direction(val degrees: Int) {
    NORTH(0), SOUTH(180), EAST(90), WEST(270)
}

sealed class Seal
class SeaLion : Seal()
class Walrus : Seal()

fun matchSeal(seal: Seal): String {
    return when(seal) {
        is Walrus -> "walrus"
        is SeaLion -> "sea lion"
    }
}
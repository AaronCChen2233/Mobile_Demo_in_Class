package Focatorymethod

interface OperatingSystem {
    fun sepecification(): String
}

class Android : OperatingSystem {
    override fun sepecification(): String {
        return "Android"
    }
}

class IOS : OperatingSystem {
    override fun sepecification(): String {
        return "IOS"
    }
}

class Windows : OperatingSystem {
    override fun sepecification(): String {
        return "Windows"
    }
}

class OSFactory {
    companion object {
        fun getInstance(str: String): OperatingSystem {
            return when (str) {
                "Android" -> Android()
                "IOS" -> IOS()
                "Windows" -> Windows()
                else -> Android()
            }
        }
    }
}

fun main() {
    val str: String = "Android"
    val currentOS: OperatingSystem = OSFactory.getInstance(str)

}
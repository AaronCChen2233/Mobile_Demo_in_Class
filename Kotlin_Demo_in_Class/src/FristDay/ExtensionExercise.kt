package FristDay

infix fun <T> T.eq(other: T) {
    if(this == other){
        println("OK")
    }
    else{
        println("Error: "+ this.toString()+" != "+other.toString())
    }
}
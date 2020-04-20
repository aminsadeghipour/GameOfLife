var world = arrayOf<Array<Boolean>>()
const val START = 0
const val END = 29
const val LIVE_CELL_PERCENTAGE = 40
const val TRUE_STRING = "* "
const val FALSE_STRING= "  "
fun main() {
    initWorld()
    while (true) {
        drawTheWorld()
        gameOfLife()
        Thread.sleep(1000)
    }
}
fun gameOfLife(){
    val worldCopy = copyTheWoeld()
    for(i in START..END) {
        for (j in START..END) {
            val neighbors = countNeighbors(worldCopy,i,j)
            if(worldCopy[i][j])
            {
               world[i][j]= neighbors==2 || neighbors==3
            }
            else{
                world[i][j]= neighbors==3
            }
        }
    }
}
fun countNeighbors(worldCopy : Array<Array<Boolean>>, i:Int , j:Int):Int{
    var neighbors =0
    var im1 = i-1
    var jm1 = j-1
    var ip1 = i+1
    var jp1 = j+1

    if(im1 < START)
        im1 = END
    if(ip1 > END)
        ip1 = START
    if(jm1 < START)
        jm1 = END
    if(jp1 > END)
        jp1 = START

    if(worldCopy[im1][j])
        neighbors++
    if(worldCopy[i][jm1])
        neighbors++
    if(worldCopy[ip1][j])
        neighbors++
    if(worldCopy[i][jp1])
        neighbors++
    if(worldCopy[im1][jm1])
        neighbors++
    if(worldCopy[ip1][jp1])
        neighbors++
    if(worldCopy[im1][jp1])
        neighbors++
    if(worldCopy[ip1][jm1])
        neighbors++

    return neighbors

}
fun copyTheWoeld(): Array<Array<Boolean>>{
    var worldCopy = arrayOf<Array<Boolean>>()
    for(i in START..END) {
        var internalArr = arrayOf<Boolean>()
        for (j in START..END) {
            internalArr += world[i][j]
        }
        worldCopy += internalArr
    }
    return worldCopy
}
fun drawTheWorld(){
    for(i in START..END){
        for (j in START..END)
        {
            print(convertToString(world[i][j]))
        }
       println()
    }
    println("-------------------------------------------------------------------------------------")
}
fun convertToString(bool : Boolean):String =
    if (bool ) TRUE_STRING else FALSE_STRING

fun initWorld(){

    for(i in START..END){
        var internalArr = arrayOf<Boolean>()
        for (j in START..END)
        {
            internalArr += randomGen()
        }
        world += internalArr
    }
}
fun randomGen():Boolean =
    Math.random() * 100 <= LIVE_CELL_PERCENTAGE

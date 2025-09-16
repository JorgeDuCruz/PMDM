package example.myapp

class Shark: FishColor, FishAction {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus(fishColor: FishColor = GoldColor):  FishAction by PrintingFishAction("eat algae"), FishColor by fishColor

interface FishAction{
    fun eat()
}
interface FishColor{
    val color: String
}

interface AquariumAction {
    fun eat()
    fun jump()
    fun clean()
    fun catchFish()
    fun swim()  {
        println("swim")
    }
}

//Clase que solo se instancia una vez para ahorrar recursos(Supongo) porque siempre hace lo mismo
object GoldColor : FishColor {
    override val color = "gold"
}

class  PrintingFishAction(val food: String): FishAction{
    override fun eat() {
        println(food)
    }
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

fun main(){
    val prueba = SeaLion()
    println(matchSeal(prueba))
}
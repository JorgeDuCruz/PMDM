package example.myapp

class Shark: FishColor, FishAction {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus(fishColor: FishColor = GoldColor):  FishAction, FishColor by fishColor {
    override fun eat() {
        println("eat algae")
    }
}

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
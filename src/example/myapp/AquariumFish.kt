package example.myapp

class Shark: FishColor, FishAction {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus: FishColor, FishAction {
    override val color = "gold"
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
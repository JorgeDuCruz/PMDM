package example.myapp

class Aquarium (var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    var volume: Int
        get() = width * height * length / 1000  // 1000 cm^3 = 1 l
        set(value){
            height = (value * 1000) / (width * length)
        }
    init {
        println("aquarium initializing")
    }

    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")

        // 1 l = 1000 cm^3
        println("Volume: $volume l")

    }

    constructor(numberOfFish: Int) : this() {
        // 2,000 cm^3 por pez + espacio extra para no derramar agua
        val tank = numberOfFish * 2000 * 1.1

        // calcular la altura necesaria
        height = (tank / (length * width)).toInt()

    }

}



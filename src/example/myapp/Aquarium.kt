package example.myapp

class Aquarium (var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    init {
        println("aquarium initializing")
    }
    init {
        // 1 liter = 1000 cm^3
        println("Volume: ${width * length * height / 1000} l")
    }

    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
    }

    constructor(numberOfFish: Int) : this() {
        // 2,000 cm^3 por pez + espacio extra para no derramar agua
        val tank = numberOfFish * 2000 * 1.1

        // calcular la altura necesaria
        height = (tank / (length * width)).toInt()

    }

}



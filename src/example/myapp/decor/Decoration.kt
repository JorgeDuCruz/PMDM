package example.myapp.decor

data class Decoration(val rocks: String) {
}

fun makeDecorations() {
    val decoration1 = Decoration("granite")
    println(decoration1)

    val decoration2 = Decoration("slate")
    println(decoration2)

    val decoration3 = Decoration("slate")
    println(decoration3)

    println (decoration1.equals(decoration2))
    println (decoration3.equals(decoration2))
}

fun main(){
    makeDecorations2()
}

// Here is a data class with 3 properties.
data class Decoration2(val rocks: String, val wood: String, val diver: String){
}

fun makeDecorations2() {
    val d5 = Decoration2("crystal", "forest", "car")
    println(d5)

// Asigna las variables segun el orden de aparicion a menos que coincidadan los nombres. Si coninciden, cada variable toma el valor de su homologo en la clase datos
    val (rock, diver,wood) = d5
    println(rock)
    println(wood)
    println(diver)

    //Puedes omitir variables con un barra baja "_"
    val (rock1, _, diver1) = d5

}
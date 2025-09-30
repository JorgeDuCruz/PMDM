import maquinaCafe.sc

interface ICoffeeMachineState {
    fun onEnter(maquinaCafe: maquinaCafe)
}
sealed class maquinaCafeSealed:ICoffeeMachineState {
    object Idle : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Máquina encendida. Empezando a hacer café...")
            MostrarOpciones()
        }
    }
    object HaciendoCafe : maquinaCafeSealed(){
        var hecho = false // Variable para rastrear si el café ya está hecho
        override fun onEnter(maquinaCafe: maquinaCafe) {
            if (!hecho) {
                println("Haciendo cafe")
                Thread.sleep(200)
                println("Cafe hecho")
                hecho = true
            } else {
                println("Café listo. ¿Lo recoges? [s/n]")
                val recoger = maquinaCafe.sc.next()
                if (recoger.lowercase().contains("s")) {
                    hecho=false // Reset hecho for next time
                    RecogerCafe()
                }
            }
        }
    }
    data class RecibiendoDinero(val dinero: Double) : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Tienes suficiente dinero? [s/n]")
            val dineroSuficiente = maquinaCafe.sc.next()
            println("¿Dinero suficiente? $dineroSuficiente")
            if (dineroSuficiente.lowercase().contains("s")) {
                Pagar(dinero)
            } else {
                CancelarCompra()
            }
        }
    }
    data class EligiendoCafe(val tipoCafe: List<String>, val precio: List<Double>) : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Te gustan las opciones? [s/n]")
            val gustanOpcion = maquinaCafe.sc.next()
            if (gustanOpcion.lowercase().contains("s")) {
                ElegirCafe(precio,tipoCafe)
            } else {
                CancelarCompra()
            }
        }
    }
    object LimpiandoMaquina : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Limpiando maquina")
            Clean()
        }
    }
}

/**
 * Permite al usuario elegir un café de las opciones disponibles.
 * Cambia el estado a `RecibiendoDinero` con el precio del café elegido.
 */
private fun ElegirCafe(listaPrecios: List<Double>,listaNombres: List<String>) {
    println("Elige una opción (1-4): ")
    val opcioElegida = sc.nextInt() - 1
    val precioCafe = listaPrecios[opcioElegida]
    val nombreCafe = listaNombres[opcioElegida]
    println("Café elegido: $nombreCafe - Precio: $precioCafe €")
    maquinaCafe.setState(maquinaCafeSealed.RecibiendoDinero(precioCafe))
}



/**
 * Muestra las opciones de café disponibles y sus precios.
 * Cambia el estado a `EligiendoCafe`.
 */
private fun MostrarOpciones() {
    val tiposCafe = listOf("Espresso", "Latte", "Cappuccino", "Americano")
    val preciosCafe = listOf(2.00, 3.00, 3.50, 2.50)
    for (i in tiposCafe.indices) {
        println("${i + 1}. ${tiposCafe[i]} - \$${preciosCafe[i]}")
    }
    maquinaCafe.setState(maquinaCafeSealed.EligiendoCafe(tiposCafe, preciosCafe))
}



/**
 * Simula el proceso de pago. Cambia el estado a `HaciendoCafe` una vez que se recibe suficiente dinero.
 */
private fun Pagar(precio : Double) {
    var dineroRecibido = 0.0
    while (dineroRecibido < precio) {
        println("Introduce dinero (actual: \$$dineroRecibido dinero necesario: $precio): ")
        dineroRecibido += sc.nextDouble()
    }
    maquinaCafe.setState(maquinaCafeSealed.HaciendoCafe)
}



/**
 * Simula la recolección del café. Cambia el estado a `LimpiandoMaquina` y luego a `Idle`.
 */
private fun RecogerCafe() {
    println("Cafe recogido")
    maquinaCafe.setState(maquinaCafeSealed.LimpiandoMaquina)
    Clean()
}


/**
 * Simula la limpieza de la máquina de café. Cambia el estado a `Idle` al finalizar.
 */
private fun Clean() {
    println("Limpiando la máquina...")
    maquinaCafe.setState(maquinaCafeSealed.Idle)
}


/**
 * Cancela la compra actual y cambia el estado a `Idle`.
 */
private fun CancelarCompra() {
    println("Compra cancelada. Volviendo a estado Idle.")
    maquinaCafe.setState(maquinaCafeSealed.Idle)
}
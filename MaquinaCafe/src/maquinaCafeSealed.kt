import maquinaCafe.currentState
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
                if ("s" == recoger.lowercase()) {
                    hecho=false // Reset hecho for next time
                    recogerCafe()
                }
            }
        }
    }
    data class RecibiendoDinero(val dinero: Double) : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Tienes suficiente dinero? [s/n]")
            val dineroSuficiente = maquinaCafe.sc.next()
            println("¿Dinero suficiente? $dineroSuficiente")
            if ("s" == dineroSuficiente.lowercase()) {
                pagar()
            } else {
                CancelarCompra()
            }
        }
    }
    data class EligiendoCafe(val tipoCafe: List<String>, val precio: List<Double>) : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Te gustan las opciones? [s/n]")
            val gustanOpcion = maquinaCafe.sc.next()
            if ("s" == gustanOpcion.lowercase()) {
                ElegirCafe()
            } else {
                CancelarCompra()
            }
        }
    }
    object LimpiandoMaquina : maquinaCafeSealed(){
        override fun onEnter(maquinaCafe: maquinaCafe) {
            println("Limpiando maquina")
        }
    }
}

/**
 * Permite al usuario elegir un café de las opciones disponibles.
 * Cambia el estado a `RecibiendoDinero` con el precio del café elegido.
 */
fun ElegirCafe() {
    println("Elige una opción (1-4): ")
    val opcioElegida = sc.nextInt() - 1
    val precioCafe = (currentState as maquinaCafeSealed.EligiendoCafe).precio[opcioElegida]
    println("Café elegido: ${(currentState as maquinaCafeSealed.EligiendoCafe).tipoCafe[opcioElegida]} - Precio: \$$precioCafe")
    currentState = maquinaCafeSealed.RecibiendoDinero(precioCafe)
}



/**
 * Muestra las opciones de café disponibles y sus precios.
 * Cambia el estado a `EligiendoCafe`.
 */
fun MostrarOpciones() {
    val tiposCafe = listOf("Espresso", "Latte", "Cappuccino", "Americano")
    val preciosCafe = listOf(2.00, 3.00, 3.50, 2.50)
    for (i in tiposCafe.indices) {
        println("${i + 1}. ${tiposCafe[i]} - \$${preciosCafe[i]}")
    }
    currentState = maquinaCafeSealed.EligiendoCafe(tiposCafe, preciosCafe)
}



/**
 * Simula el proceso de pago. Cambia el estado a `HaciendoCafe` una vez que se recibe suficiente dinero.
 */
fun pagar() {
    var dineroRecibido = 0.0
    while (dineroRecibido <= (currentState as maquinaCafeSealed.RecibiendoDinero).dinero) {
        println("Introduce dinero (actual: \$$dineroRecibido) dinero necesario: \$${(currentState as maquinaCafeSealed.RecibiendoDinero).dinero}): ")
        dineroRecibido += sc.nextDouble()
    }
    currentState = maquinaCafeSealed.HaciendoCafe
}



/**
 * Simula la recolección del café. Cambia el estado a `LimpiandoMaquina` y luego a `Idle`.
 */
fun recogerCafe() {
    println("Cafe recogido")
    currentState = maquinaCafeSealed.LimpiandoMaquina
    clean()
}


/**
 * Simula la limpieza de la máquina de café. Cambia el estado a `Idle` al finalizar.
 */
fun clean() {
    println("Limpiando la máquina...")
    currentState = maquinaCafeSealed.Idle
    println("Máquina limpia. Estado: $currentState")
}


/**
 * Cancela la compra actual y cambia el estado a `Idle`.
 */
fun CancelarCompra() {
    println("Compra cancelada. Volviendo a estado Idle.")
    currentState = maquinaCafeSealed.Idle
}
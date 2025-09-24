import java.util.Locale
import java.util.Locale.getDefault
import java.util.Scanner

object maquinaCafe {
    public var currentState: maquinaCafeSealed = maquinaCafeSealed.Idle
    private val sc = Scanner (System.`in`)
    private var hecho = false

    fun ActualizarEstado() {
        println("Estado actual: $currentState")

        when (currentState) {
            is maquinaCafeSealed.Idle -> {
                println("Máquina encendida. Empezando a hacer café...")
                MostrarOpciones()
            }
            is maquinaCafeSealed.EligiendoCafe -> {
                println("Te gustan las opciones? [s/n]")
                var gustanOpcion = sc.next()
                if ("s" == gustanOpcion.lowercase()){
                    ElegirCafe()
                }
                else{
                    CancelarCompra()
                }

            }
            is maquinaCafeSealed.HaciendoCafe -> {
                if (!hecho){
                    println("Haciendo cafe")
                    Thread.sleep(200)
                    println("Cafe hecho")
                    hecho = true
                }else {
                    println("Café listo. ¿Lo recoges? [s/n]")
                    var recoger = sc.next()
                    if ("s" == recoger.lowercase()) {
                        recogerCafe()
                    }
                }
            }
            is maquinaCafeSealed.RecibiendoDinero -> {
                println("Tienes suficiente dinero? [s/n]")
                var dineroSuficiente = sc.next()
                println("¿Dinero suficiente? $dineroSuficiente")
                if ("s" == dineroSuficiente.lowercase()) {
                    pagar()
                }
                else{
                    CancelarCompra()
                }
            }
            is maquinaCafeSealed.LimpiandoMaquina -> {
                println("Limpiando maquina")
            }
        }
    }

    fun ElegirCafe() {
        println("Elige una opción (1-4): ")
        val opcioElegida = sc.nextInt()-1
        val precioCafe = (currentState as maquinaCafeSealed.EligiendoCafe).precio[opcioElegida]
        println("Café elegido: ${(currentState as maquinaCafeSealed.EligiendoCafe).tipoCafe[opcioElegida]} - Precio: \$$precioCafe")
        currentState = maquinaCafeSealed.RecibiendoDinero(precioCafe)
    }
    fun MostrarOpciones (){
        val tiposCafe = listOf("Espresso", "Latte", "Cappuccino", "Americano")
        val preciosCafe = listOf(2.00, 3.00, 3.50, 2.50)
        for (i in tiposCafe.indices) {
            println("${i + 1}. ${tiposCafe[i]} - \$${preciosCafe[i]}")
        }
        currentState = maquinaCafeSealed.EligiendoCafe(tiposCafe, preciosCafe)

    }

    fun pagar() {
        var dineroRecibido = 0.0
        while (dineroRecibido <= (currentState as maquinaCafeSealed.RecibiendoDinero).dinero) {
            println("Introduce dinero (actual: \$$dineroRecibido) dinero necesario: \$${(currentState as maquinaCafeSealed.RecibiendoDinero).dinero}): ")
            dineroRecibido += sc.nextDouble()
        }
        currentState = maquinaCafeSealed.HaciendoCafe
    }
    fun recogerCafe(){
        println("Cafe recogido")
        currentState = maquinaCafeSealed.LimpiandoMaquina
        clean()
    }
    fun clean() {
        println("Limpiando la máquina...")
        currentState = maquinaCafeSealed.Idle
        println("Máquina limpia. Estado: $currentState")
    }

    fun CancelarCompra(){
        println("Compra cancelada. Volviendo a estado Idle.")
        currentState = maquinaCafeSealed.Idle
    }
}
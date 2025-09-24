object maquinaCafe {
    public var currentState: maquinaCafeSealed = maquinaCafeSealed.Idle

    fun ActualizarEstado() {
        println("Estado actual: $currentState")

        when (currentState) {
            is maquinaCafeSealed.Idle -> {
                println("Máquina encendida. Empezando a hacer café...")
                MostrarOpciones()
            }
            is maquinaCafeSealed.EligiendoCafe -> {
                var gustanOpcion = listOf(true, false).random()
                if (gustanOpcion){
                    ElegirCafe()
                }
                else{
                    CancelarCompra()
                }

            }
            is maquinaCafeSealed.HaciendoCafe -> {
                println("Haciendo cafe")
                Thread.sleep(200)
                println("Cafe hecho")
                var recoger = listOf(true, false).random()
                if (recoger) {
                    recogerCafe()
                }
            }
            is maquinaCafeSealed.RecibiendoDinero -> {
                val dineroSuficiente = listOf(true, false).random()
                if (dineroSuficiente) {
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
        val opcioElegida = ((currentState as maquinaCafeSealed.EligiendoCafe).tipoCafe.indices).random()
        val precioCafe = (currentState as maquinaCafeSealed.EligiendoCafe).precio[opcioElegida]
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
            dineroRecibido +=0.01
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
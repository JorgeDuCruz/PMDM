object maquinaCafe {
    public var currentState: maquinaCafeSealed = maquinaCafeSealed.Idle

    fun ActualizarEstado() {
        println("Estado actual: $currentState")

        when (currentState) {
            is maquinaCafeSealed.Idle -> {
                println("Máquina encendida. Empezando a hacer café...")
                currentState = maquinaCafeSealed.EligiendoCafe("Americano")
                ActualizarEstado()

                println("¡Café listo! Estado: $currentState")
            }
            is maquinaCafeSealed.EligiendoCafe -> {
                println("Cafe elegido: "+(currentState as maquinaCafeSealed.EligiendoCafe).tipoCafe)
                currentState = maquinaCafeSealed.RecibiendoDinero(2.56)
                ActualizarEstado()
            }
            is maquinaCafeSealed.HaciendoCafe -> {
                println("Haciendo cafe")
            }
            is maquinaCafeSealed.RecibiendoDinero -> {
                println("Recibiendo "+(currentState as maquinaCafeSealed.RecibiendoDinero).dinero+"€")
                currentState = maquinaCafeSealed.HaciendoCafe
                ActualizarEstado()
            }
            is maquinaCafeSealed.LimpiandoMaquina -> {
                println("Limpiando maquina")
            }
        }
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
}
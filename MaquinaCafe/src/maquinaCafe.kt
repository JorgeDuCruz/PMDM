import java.util.Scanner

/**
 * Objeto que representa una máquina de café con diferentes estados y funcionalidades.
 */
object maquinaCafe {

    var currentState: maquinaCafeSealed = maquinaCafeSealed.Idle // Estado actual de la máquina, por defecto es Idle
    val sc = Scanner(System.`in`) // Scanner para leer la entrada del usuario

    fun setState(newState: maquinaCafeSealed) {
        if (esTransicionValida(currentState, newState)) {
            currentState = newState
            if (!(currentState == maquinaCafeSealed.Idle)){ // Si vuelve al estado Idle termina la ejecucion para no estar con un bucle infinito
                ActualizarEstado()
            }
        } else {
            println("Transición inválida de $currentState a $newState")
        }
    }
    /**
     * Actualiza el estado de la máquina de café en función de su estado actual.
     */
    fun ActualizarEstado() {
        println("Actualizando estado")
        currentState.onEnter(this)
    }

    /**
     * Verifica si la transición entre dos estados es válida.
     * @param from Estado actual.
     * @param to Estado al que se quiere transicionar.
     * @return true si la transición es válida, false en caso contrario.
     */
    fun esTransicionValida(from: maquinaCafeSealed, to: maquinaCafeSealed): Boolean {
        return when (from) {
            is maquinaCafeSealed.Idle -> to is maquinaCafeSealed.EligiendoCafe
            is maquinaCafeSealed.EligiendoCafe -> to is maquinaCafeSealed.RecibiendoDinero || to is maquinaCafeSealed.Idle
            is maquinaCafeSealed.RecibiendoDinero -> to is maquinaCafeSealed.HaciendoCafe || to is maquinaCafeSealed.Idle
            is maquinaCafeSealed.HaciendoCafe -> to is maquinaCafeSealed.LimpiandoMaquina
            is maquinaCafeSealed.LimpiandoMaquina -> to is maquinaCafeSealed.Idle
            else -> false
        }
    }

    fun getState(): maquinaCafeSealed {
        return currentState
    }
}

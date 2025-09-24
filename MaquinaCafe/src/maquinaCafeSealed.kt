sealed class maquinaCafeSealed {
    object Idle : maquinaCafeSealed()
    object HaciendoCafe : maquinaCafeSealed()
    data class RecibiendoDinero(val dinero: Double) : maquinaCafeSealed()
    data class EligiendoCafe(val tipoCafe: List<String>, val precio: List<Double>) : maquinaCafeSealed()
    object LimpiandoMaquina : maquinaCafeSealed()
}
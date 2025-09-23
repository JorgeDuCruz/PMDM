sealed class maquinaCafeSealed {
    object Idle : maquinaCafeSealed()
    object HaciendoCafe : maquinaCafeSealed()
    data class RecibiendoDinero(val dinero: Double) : maquinaCafeSealed()
    data class EligiendoCafe(val tipoCafe: String) : maquinaCafeSealed()
    object LimpiandoMaquina : maquinaCafeSealed()
}
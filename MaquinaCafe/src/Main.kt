//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Encendiendo maquina")
    maquinaCafe.ActualizarEstado()
    println("cafe de nuevo")
    maquinaCafe.ActualizarEstado()
    println("limpiando maquina")
    maquinaCafe.clean()
    println("Otro cafe más")
    maquinaCafe.ActualizarEstado()

    maquinaCafe.ActualizarEstado()
}
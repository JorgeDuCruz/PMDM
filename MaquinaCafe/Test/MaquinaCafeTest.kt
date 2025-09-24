import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class MaquinaCafeTest {

    @Test
    fun actualizarEstadoFromEligiendoCafeToRecibiendoDineroCuandoUsuarioAcepta() {
        maquinaCafe.currentState = maquinaCafeSealed.EligiendoCafe(
            listOf("Espresso", "Latte"), listOf(2.0, 3.0)
        )
        System.setIn(ByteArrayInputStream("s\n1\n".toByteArray()))
        maquinaCafe.ActualizarEstado()
        maquinaCafe.ElegirCafe()
        assert(maquinaCafe.currentState is maquinaCafeSealed.RecibiendoDinero)
    }

    @Test
    fun actualizarEstadoFromEligiendoCafeToIdleCuandoUsuarioCancela() {
        maquinaCafe.currentState = maquinaCafeSealed.EligiendoCafe(
            listOf("Espresso", "Latte"), listOf(2.0, 3.0)
        )
        System.setIn(ByteArrayInputStream("n\n".toByteArray()))
        maquinaCafe.ActualizarEstado()
        assertEquals(maquinaCafeSealed.Idle, maquinaCafe.currentState)
    }

    @Test
    fun actualizarEstadoFromRecibiendoDineroToHaciendoCafeCuandoUsuarioPaga() {
        maquinaCafe.currentState = maquinaCafeSealed.RecibiendoDinero(2.0)
        System.setIn(ByteArrayInputStream("s\n2.0\n".toByteArray()))
        maquinaCafe.ActualizarEstado()
        maquinaCafe.pagar()
        assertEquals(maquinaCafeSealed.HaciendoCafe, maquinaCafe.currentState)
    }

    @Test
    fun actualizarEstadoFromRecibiendoDineroToIdleCuandoUsuarioNoPaga() {
        maquinaCafe.currentState = maquinaCafeSealed.RecibiendoDinero(2.0)
        System.setIn(ByteArrayInputStream("n\n".toByteArray()))
        maquinaCafe.ActualizarEstado()
        assertEquals(maquinaCafeSealed.Idle, maquinaCafe.currentState)
    }

    @Test
    fun actualizarEstadoFromHaciendoCafeToLimpiandoMaquinaCuandoUsuarioRecogeCafe() {
        maquinaCafe.currentState = maquinaCafeSealed.HaciendoCafe
        maquinaCafe.hecho = true
        System.setIn(ByteArrayInputStream("s\n".toByteArray()))
        maquinaCafe.ActualizarEstado()
        assert(maquinaCafe.currentState is maquinaCafeSealed.LimpiandoMaquina)
    }

}
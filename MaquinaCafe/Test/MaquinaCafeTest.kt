import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MaquinaCafeTest {

    @Test
    fun actualizarEstadoFromRecibiendoDineroToHaciendoCafe() {
        maquinaCafe.currentState = maquinaCafeSealed.RecibiendoDinero(2.50)
        maquinaCafe.ActualizarEstado()
        assertEquals(maquinaCafeSealed.HaciendoCafe, maquinaCafe.currentState)
    }


    @Test
    fun cleanTransitionsToIdle() {
        maquinaCafe.currentState = maquinaCafeSealed.LimpiandoMaquina
        maquinaCafe.clean()
        assertEquals(maquinaCafeSealed.Idle, maquinaCafe.currentState)
    }


    @Test
    fun actualizarEstadoFromRecibiendoDineroToIdleWhenInsufficientMoney() {
        maquinaCafe.currentState = maquinaCafeSealed.RecibiendoDinero(3.00)
        maquinaCafe.ActualizarEstado()
        assertEquals(maquinaCafeSealed.Idle, maquinaCafe.currentState)
    }

    @Test
    fun actualizarEstadoFromHaciendoCafeToIdleWhenCoffeeNotCollected() {
        maquinaCafe.currentState = maquinaCafeSealed.HaciendoCafe
        maquinaCafe.ActualizarEstado()
        assertEquals(maquinaCafeSealed.Idle, maquinaCafe.currentState)
    }
}
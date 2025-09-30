# Máquina cafe

## Diagrama

```mermaid
stateDiagram-v2
    [*] --> Idle
    Idle --> EligiendoCafe: MostrarOpciones()
    EligiendoCafe --> Idle: CancelarCompra()
    EligiendoCafe --> RecibiendoDinero:ElegirCafe(listaPrecios,listaNombres)
    RecibiendoDinero --> Idle: CancelarCompra()
    RecibiendoDinero --> HaciendoCafe: Pagar(precio)
    HaciendoCafe --> LimpiandoMaquina: RecogerCafe()
    LimpiandoMaquina --> Idle:Clean()
    Idle --> [*]: Vuelve a Idle
    HaciendoCafe -->[*]:No recoje cafe
```
Este diagrama es una representación del estado actual del código.
Idealmente `HaciendoCafe` no terminaria el programa, pero
si no se hace asi entoces entraria en bucle hasta que recojas el cafe y no quiero un bucle infinito.
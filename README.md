# 1. Título y Temática Elegida
**Turnos RPG: Mazmorras en Consola**
Juego de rol (RPG) táctico por turnos simulado en consola. El jugador explora una cuadrícula invisible y combate enemigos al coincidir en las mismas coordenadas.

# 2. Arquitectura del Software
Se ha optado por un diseño orientado a objetos con 5 clases, priorizando la encapsulación y la separación de responsabilidades:
- **Main**: Simula el bucle principal y enruta los inputs.
- **MotorJuego**: Clase cerebro. Controla los estados (MENU, JUGANDO, PAUSA, GAMEOVER), gestiona las entidades (añadir/eliminar) y la lógica matemática de colisiones.
- **EntidadVideojuego**: Centraliza los datos espaciales (x, y, w, h) y atributos vitales de cualquier elemento (Jugador, Enemigo, Item).
- **GestorEntradas**: Aísla la capa de captura de comandos de usuario (simulador táctil).
- **SistemaGuardado**: Extrae la lógica de persistencia de datos (Advanced Feature) para no acoplarla al Motor.

# 3. Diagrama de Clases UML

```mermaid
classDiagram
    class Main {
        +main(args: String[]) void
    }
    class MotorJuego {
        -estadoJuego: String
        -jugador: EntidadVideojuego
        -listaEntidades: List~EntidadVideojuego~
        -sistemaGuardado: SistemaGuardado
        +iniciarPartida() void
        +pausarPartida() void
        +reanudarPartida() void
        +forzarGameOver() void
        +añadirEntidad(e: EntidadVideojuego) void
        +eliminarEntidad(e: EntidadVideojuego) void
        +desplazarEntidad(direccion: String) void
        +pulsarBotonAccion() void
        +actualizar() void
        -verificarColisiones() void
        +getEstado() String
    }
    class EntidadVideojuego {
        -nombre: String
        -tipo: String
        -x: int
        -y: int
        -w: int
        -h: int
        -puntosVida: int
        -sprite: String
        +getX() int
        +setX(x: int) void
        +getY() int
        +setY(y: int) void
        +getPuntosVida() int
        +setPuntosVida(hp: int) void
    }
    class GestorEntradas {
        -comandosSimulados: String[]
        -indice: int
        +obtenerSiguienteComando() String
    }
    class SistemaGuardado {
        +generarGuardadoRapido(jugador: EntidadVideojuego, entidades: List) void
    }

    Main ..> MotorJuego : Ejecuta
    Main ..> GestorEntradas : Lee
    MotorJuego "1" --> "*" EntidadVideojuego : Gestiona
    MotorJuego --> "1" SistemaGuardado : Utiliza

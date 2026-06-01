# ⚔️ Turnos RPG: Mazmorras en Consola

**Temática:** Juego de rol (RPG) táctico por turnos simulado en consola. El jugador explora una cuadrícula invisible y combate enemigos al coincidir en las mismas coordenadas.

## 🏛️ Arquitectura del Software
Se ha optado por un diseño orientado a objetos minimalista con 5 clases, priorizando la separación de responsabilidades:
- `Main`: Simula el entorno de ejecución y bucle principal.
- `MotorJuego`: Controla el ciclo de vida, estados y la lógica matemática (colisiones).
- `EntidadVideojuego`: Centraliza los datos espaciales y atributos vitales de cualquier elemento (Player o NPC).
- `GestorEntradas`: Aísla la capa de captura de comandos de usuario.
- `SistemaGuardado`: Extrae la lógica de persistencia de datos (Advanced Feature) para no acoplarla al Motor.

## 📊 Diagrama de Clases UML (Mermaid)

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
        +actualizar(input: String) void
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

    Main --> MotorJuego : Ejecuta
    Main --> GestorEntradas : Lee
    MotorJuego --> EntidadVideojuego : Gestiona
    MotorJuego --> SistemaGuardado : Utiliza

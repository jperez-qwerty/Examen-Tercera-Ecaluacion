# Motor de Videojuego 2D - Simulación de Motor de Juego

## 1. Temática Elegida

Este proyecto simula el funcionamiento básico de un motor de videojuego 2D. El sistema permite gestionar el ciclo de vida de una partida mediante distintos estados (menú, juego, pausa y game over), controlar el movimiento del jugador sobre un mapa, interactuar con objetos del entorno, detectar colisiones con enemigos y realizar guardados rápidos del estado actual de la partida.

La simulación se ejecuta mediante una secuencia de entradas predefinidas que representan las acciones que realizaría un jugador durante una sesión de juego.

---

# 2. Arquitectura del Software

La aplicación sigue una arquitectura orientada a objetos basada en la separación de responsabilidades.

## Main

Clase principal encargada de iniciar la aplicación.

Responsabilidades:

* Crear las instancias principales del sistema.
* Inicializar el motor de juego.
* Gestionar el bucle principal.
* Procesar los comandos recibidos desde el gestor de entradas.

---

## MotorJuego

Es la clase central del sistema.

Responsabilidades:

* Gestionar los estados del juego.
* Controlar la partida.
* Gestionar las entidades existentes.
* Procesar los movimientos del jugador.
* Detectar colisiones.
* Coordinar el sistema de guardado.

Representa el núcleo del motor de juego.

---

## EntidadVideojuego

Representa cualquier objeto existente dentro del mundo del juego.

Responsabilidades:

* Almacenar posición.
* Almacenar puntos de vida.
* Identificar el tipo de entidad.
* Proporcionar acceso controlado a sus atributos.

Puede representar:

* Jugador.
* Enemigo.
* Objeto coleccionable.

---

## GestorEntradas

Simula las entradas del usuario.

Responsabilidades:

* Generar comandos.
* Proporcionar las acciones que ejecutará el jugador.
* Separar la lógica de entrada de la lógica del motor.

---

## SistemaGuardado

Gestiona el guardado rápido de la partida.

Responsabilidades:

* Recopilar el estado actual del juego.
* Generar una representación JSON del guardado.
* Mostrar la información almacenada.

---

# 3. Diagrama de Clases UML

```mermaid
classDiagram

class Main {
    +main(String[] args)
}

class MotorJuego {
    -String estadoJuego
    -EntidadVideojuego jugador
    -List~EntidadVideojuego~ listaEntidades
    -SistemaGuardado sistemaGuardado

    +iniciarPartida()
    +pausarPartida()
    +reanudarPartida()
    +forzarGameOver()
    +añadirEntidad(e)
    +eliminarEntidad(e)
    +desplazarEntidad(direccion)
    +pulsarBotonAccion()
    +actualizar()
    +getEstado()
    +getJugador()
    +getListaEntidades()
    +getSistemaGuardado()
}

class EntidadVideojuego {
    -String nombre
    -String tipo
    -int x
    -int y
    -int w
    -int h
    -int puntosVida
    -String sprite

    +getNombre()
    +getTipo()
    +getX()
    +setX()
    +getY()
    +setY()
    +getPuntosVida()
    +setPuntosVida()
    +getSprite()
    +toString()
}

class GestorEntradas {
    -String[] comandosSimulados
    -int indice

    +obtenerSiguienteComando()
}

class SistemaGuardado {
    +generarGuardadoRapido()
}

Main --> MotorJuego
Main --> GestorEntradas

MotorJuego *-- EntidadVideojuego
MotorJuego --> SistemaGuardado
```

---

# 4. Diagrama de Casos de Uso UML

```mermaid
flowchart LR

Jugador((Jugador))

CU1([CU-01 Iniciar Partida])
CU2([CU-02 Desplazar Personaje])
CU3([Recolectar Objeto])
CU4([Guardar Partida])
CU5([Pausar Partida])
CU6([Reanudar Partida])

Jugador --> CU1
Jugador --> CU2
Jugador --> CU3
Jugador --> CU4
Jugador --> CU5
Jugador --> CU6
```

---

# 5. Especificación de Casos de Uso

## Caso de Uso CU-01 Iniciar Partida

| Campo               | Descripción                                                                                                                                                                                             |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Nombre              | CU-01 Iniciar Partida                                                                                                                                                                                   |
| Objetivo            | Comenzar una nueva sesión de juego.                                                                                                                                                                     |
| Actor Principal     | Jugador                                                                                                                                                                                                 |
| Precondiciones      | La aplicación está ejecutándose y el motor se encuentra en estado MENU.                                                                                                                                 |
| Flujo Principal     | 1. El jugador inicia la partida.<br>2. El sistema cambia el estado a JUGANDO.<br>3. Se crea el jugador.<br>4. Se generan las entidades iniciales del mapa.<br>5. Comienza el bucle principal del juego. |
| Flujos Alternativos | Si ocurre un error al crear las entidades, la partida no se inicia.                                                                                                                                     |
| Postcondiciones     | El juego queda en estado JUGANDO con las entidades inicializadas.                                                                                                                                       |
| Reglas de Negocio   | No puede iniciarse una nueva partida si el sistema ya está ejecutando otra.                                                                                                                             |

---

## Caso de Uso CU-02 Desplazar Personaje

| Campo               | Descripción                                                                                                                                                                                                       |
| ------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Nombre              | CU-02 Desplazar Personaje                                                                                                                                                                                         |
| Objetivo            | Modificar la posición del jugador dentro del mapa.                                                                                                                                                                |
| Actor Principal     | Jugador                                                                                                                                                                                                           |
| Precondiciones      | La partida debe encontrarse en estado JUGANDO.                                                                                                                                                                    |
| Flujo Principal     | 1. El jugador pulsa una dirección.<br>2. El sistema recibe el comando.<br>3. El motor actualiza las coordenadas del jugador.<br>4. Se ejecuta la actualización del juego.<br>5. Se verifican posibles colisiones. |
| Flujos Alternativos | Si la partida está en PAUSA el movimiento no se procesa.                                                                                                                                                          |
| Postcondiciones     | La posición del jugador queda actualizada.                                                                                                                                                                        |
| Reglas de Negocio   | Solo se permiten desplazamientos cuando el estado del juego es JUGANDO.                                                                                                                                           |

---

# 6. Tecnologías Utilizadas

* Java
* Programación Orientada a Objetos (POO)
* UML mediante Mermaid
* Git
* GitHub

---

# 7. Autor

Proyecto desarrollado como práctica académica para la asignatura de Entornos de Desarrollo.

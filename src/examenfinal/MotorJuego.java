package examenfinal;
import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private String estadoJuego; // "MENU", "JUGANDO", "PAUSA", "GAMEOVER"
    private EntidadVideojuego jugador;
    private List<EntidadVideojuego> listaEntidades;
    private SistemaGuardado sistemaGuardado;

    public MotorJuego() {
        this.estadoJuego = "MENU";
        this.listaEntidades = new ArrayList<>();
        this.sistemaGuardado = new SistemaGuardado();
    }

    public void iniciarPartida() {
        System.out.println("\n[MOTOR] Inicializando estado a JUGANDO...");
        this.estadoJuego = "JUGANDO";
        jugador = new EntidadVideojuego("Heroe", "JUGADOR", 0, 0, 100, "[H]");
        listaEntidades.add(new EntidadVideojuego("Goblin", "ENEMIGO", 1, 1, 30, "[G]"));
    }

    public void actualizar(String input) {
        if (!estadoJuego.equals("JUGANDO")) return;

        System.out.println("[MOTOR] Actualizando frame...");
        
        // Procesar Input
        switch (input) {
            case "ARRIBA": jugador.setY(jugador.getY() - 1); break;
            case "ABAJO": jugador.setY(jugador.getY() + 1); break;
            case "DERECHA": jugador.setX(jugador.getX() + 1); break;
            case "IZQUIERDA": jugador.setX(jugador.getX() - 1); break;
            case "GUARDAR": sistemaGuardado.generarGuardadoRapido(jugador, listaEntidades); return;
            case "SALIR": this.estadoJuego = "GAMEOVER"; return;
        }

        // Funcionalidad Avanzada: Detector de Colisiones Simples
        verificarColisiones();
    }

    private void verificarColisiones() {
        for (EntidadVideojuego entidad : listaEntidades) {
            if (entidad.getTipo().equals("ENEMIGO")) {
                // Misma coordenada (x, y) asumiendo w=1 y h=1
                if (jugador.getX() == entidad.getX() && jugador.getY() == entidad.getY()) {
                    System.out.println("!! [COLISIÓN DETECTADA] Has chocado con " + entidad.getNombre());
                    jugador.setPuntosVida(jugador.getPuntosVida() - 10);
                    System.out.println("!! El jugador recibe 10 de daño. HP Restante: " + jugador.getPuntosVida());
                    
                    if (jugador.getPuntosVida() <= 0) {
                        estadoJuego = "GAMEOVER";
                        System.out.println("!! [ESTADO] Has muerto.");
                    }
                }
            }
        }
    }

    public String getEstado() { return estadoJuego; }
}
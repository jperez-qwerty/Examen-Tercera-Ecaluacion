package examenfinal;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private String estadoJuego; 
    private EntidadVideojuego jugador;
    private List<EntidadVideojuego> listaEntidades;
    private SistemaGuardado sistemaGuardado;

    public MotorJuego() {
        this.estadoJuego = "MENU";
        this.listaEntidades = new ArrayList<>();
        this.sistemaGuardado = new SistemaGuardado();
    }

    // --- REQUISITO 1: CONTROL DE ESTADO ---
    public void iniciarPartida() {
        System.out.println("\n[ESTADO] Iniciando partida...");
        this.estadoJuego = "JUGANDO";
        jugador = new EntidadVideojuego("Heroe", "JUGADOR", 0, 0, 100, "[H]");
        
        // REQUISITO 3: GESTIÓN DE ENTIDADES BÁSICA (Añadir)
        añadirEntidad(new EntidadVideojuego("Goblin", "ENEMIGO", 1, 1, 30, "[G]"));
        añadirEntidad(new EntidadVideojuego("Moneda Oro", "ITEM", 0, 1, 0, "[O]"));
    }
    public void pausarPartida() { this.estadoJuego = "PAUSA"; System.out.println("\n[ESTADO] Partida PAUSADA."); }
    public void reanudarPartida() { this.estadoJuego = "JUGANDO"; System.out.println("\n[ESTADO] Partida REANUDADA."); }
    public void forzarGameOver() { this.estadoJuego = "GAMEOVER"; System.out.println("\n[ESTADO] GAME OVER forzado."); }

    // --- REQUISITO 3: GESTIÓN DE ENTIDADES BÁSICA ---
    public void añadirEntidad(EntidadVideojuego e) {
        listaEntidades.add(e);
        System.out.println("[MOTOR] Entidad generada en mapa: " + e.getNombre());
    }
    public void eliminarEntidad(EntidadVideojuego e) {
        listaEntidades.remove(e);
        System.out.println("[MOTOR] Entidad eliminada del mapa: " + e.getNombre());
    }

    // --- REQUISITO 4: SIMULACIÓN DE INPUTS TÁCTILES ---
    public void desplazarEntidad(String direccion) {
        System.out.println("[INPUT TÁCTIL] Pad direccional pulsado: " + direccion);
        switch (direccion) {
            case "ARRIBA": jugador.setY(jugador.getY() - 1); break;
            case "ABAJO": jugador.setY(jugador.getY() + 1); break;
            case "DERECHA": jugador.setX(jugador.getX() + 1); break;
            case "IZQUIERDA": jugador.setX(jugador.getX() - 1); break;
        }
    }

    public void pulsarBotonAccion() {
        System.out.println("[INPUT TÁCTIL] Botón 'ACCION' pulsado en pantalla.");
        EntidadVideojuego itemRecolectado = null;
        for (EntidadVideojuego entidad : listaEntidades) {
            if (entidad.getTipo().equals("ITEM") && jugador.getX() == entidad.getX() && jugador.getY() == entidad.getY()) {
                System.out.println("!! Acción completada: Has recogido " + entidad.getNombre());
                itemRecolectado = entidad;
                break;
            }
        }
        if (itemRecolectado != null) eliminarEntidad(itemRecolectado); // Requisito 3 (Eliminar)
    }

    // --- REQUISITO 2: SIMULACIÓN DEL BUCLE (GAME LOOP) ---
    public void actualizar() {
        if (!estadoJuego.equals("JUGANDO")) return;

        System.out.println("[GAME LOOP] Actualizando posiciones y log de entidades...");
        System.out.println(" -> " + jugador.toString());
        for (EntidadVideojuego entidad : listaEntidades) {
            System.out.println(" -> " + entidad.toString());
        }
        
        verificarColisiones(); // Funcionalidad Avanzada
    }

    private void verificarColisiones() {
        for (EntidadVideojuego entidad : listaEntidades) {
            if (entidad.getTipo().equals("ENEMIGO") && jugador.getX() == entidad.getX() && jugador.getY() == entidad.getY()) {
                System.out.println("!! [COLISIÓN] Combate contra " + entidad.getNombre());
                jugador.setPuntosVida(jugador.getPuntosVida() - 10);
                if (jugador.getPuntosVida() <= 0) forzarGameOver();
            }
        }
    }

    public String getEstado() { return estadoJuego; }
    public EntidadVideojuego getJugador() { return jugador; }
    public List<EntidadVideojuego> getListaEntidades() { return listaEntidades; }
    public SistemaGuardado getSistemaGuardado() { return sistemaGuardado; }
}
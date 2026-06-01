
package examenfinal;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO MOTOR 2D ===");
        
        MotorJuego motor = new MotorJuego();
        GestorEntradas inputs = new GestorEntradas();
        
        motor.iniciarPartida();
        
        // Bucle de juego condicionado al estado
        while (!motor.getEstado().equals("GAMEOVER")) {
            String comando = inputs.obtenerSiguienteComando();
            
            if (comando.equals("NADA")) break;

            // Enrutador de inputs a las funcionalidades obligatorias
            switch (comando) {
                case "PAUSA": motor.pausarPartida(); break;
                case "REANUDAR": motor.reanudarPartida(); break;
                case "GAMEOVER": motor.forzarGameOver(); break;
                case "ACCION": motor.pulsarBotonAccion(); break;
                case "GUARDAR": motor.getSistemaGuardado().generarGuardadoRapido(motor.getJugador(), motor.getListaEntidades()); break;
                default: 
                    // Si es ARRIBA, ABAJO, DERECHA, IZQUIERDA
                    if(!motor.getEstado().equals("PAUSA")) {
                        motor.desplazarEntidad(comando);
                    }
                    break;
            }
            
            motor.actualizar(); // Llamada al Game Loop requerida
            System.out.println("-----------------------------------");
            
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        
        System.out.println("=== FIN. ESTADO FINAL: " + motor.getEstado() + " ===");
    }
}
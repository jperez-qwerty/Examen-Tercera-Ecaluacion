
package examenfinal;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SIMULADOR RPG TURNOS ===");
        
        MotorJuego motor = new MotorJuego();
        GestorEntradas inputs = new GestorEntradas();
        
        motor.iniciarPartida();
        
        // Simulación del Game Loop
        while (motor.getEstado().equals("JUGANDO")) {
            String comando = inputs.obtenerSiguienteComando();
            
            if (comando.equals("NADA")) {
                System.out.println("Fin de las entradas simuladas.");
                break;
            }
            
            motor.actualizar(comando);
            
            try {
                Thread.sleep(1000); // Pausa de 1 segundo para leer la consola
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("=== FIN DE LA SIMULACIÓN. ESTADO FINAL: " + motor.getEstado() + " ===");
    }
}
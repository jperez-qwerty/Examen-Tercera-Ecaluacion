package examenfinal;
public class GestorEntradas {
    // Simulamos una secuencia que prueba todas las funcionalidades mínimas
    private String[] comandosSimulados = {
        "ABAJO", "ACCION", "PAUSA", "REANUDAR", "DERECHA", "GUARDAR", "GAMEOVER"
    };
    private int indice = 0;

    public String obtenerSiguienteComando() {
        if (indice < comandosSimulados.length) {
            return comandosSimulados[indice++];
        }
        return "NADA";
    }

}
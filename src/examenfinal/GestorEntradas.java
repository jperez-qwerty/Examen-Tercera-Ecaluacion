package examenfinal;
public class GestorEntradas {
    // Simula una secuencia predefinida de comandos para el examen
    private String[] comandosSimulados = {"ARRIBA", "DERECHA", "ACCION", "GUARDAR", "SALIR"};
    private int indice = 0;

    public String obtenerSiguienteComando() {
        if (indice < comandosSimulados.length) {
            System.out.println(">> Input simulado recibido: " + comandosSimulados[indice]);
            return comandosSimulados[indice++];
        }
        return "NADA";
    }
}
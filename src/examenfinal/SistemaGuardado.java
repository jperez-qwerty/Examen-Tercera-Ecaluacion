package examenfinal;

import java.util.List;

public class SistemaGuardado {
    public void generarGuardadoRapido(EntidadVideojuego jugador, List<EntidadVideojuego> entidades) {
        System.out.println("--- [SISTEMA] INICIANDO GUARDADO RÁPIDO ---");
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"jugador\": { \"x\": ").append(jugador.getX())
            .append(", \"y\": ").append(jugador.getY())
            .append(", \"hp\": ").append(jugador.getPuntosVida()).append(" },\n");
        json.append("  \"entidadesActivas\": ").append(entidades.size()).append("\n");
        json.append("}");
        System.out.println("Archivo de guardado generado en memoria:\n" + json.toString());
        System.out.println("-------------------------------------------");
    }
}
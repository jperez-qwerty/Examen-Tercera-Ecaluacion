package examenfinal;
public class EntidadVideojuego {
    private String nombre;
    private String tipo; // "JUGADOR", "ENEMIGO", "ITEM"
    private int x, y, w, h;
    private int puntosVida;
    private String sprite;

    public EntidadVideojuego(String nombre, String tipo, int x, int y, int puntosVida, String sprite) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.w = 1; // Tamaño base 1x1 en cuadrícula
        this.h = 1;
        this.puntosVida = puntosVida;
        this.sprite = sprite;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getPuntosVida() { return puntosVida; }
    public void setPuntosVida(int puntosVida) { this.puntosVida = puntosVida; }
    public String getSprite() { return sprite; }
    
    @Override
    public String toString() {
        return nombre + " [" + x + "," + y + "] HP:" + puntosVida;
    }
}
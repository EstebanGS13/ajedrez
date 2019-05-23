
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;

public class Jugador {

    private Ajedrez ajedrez;
    private final String nombre;
    private final Color color;

    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

    public boolean jugar(Casilla casillaInicio, Casilla casillaFin, Ficha ficha, Tablero tablero) {
        if (this.color == ficha.getColor()) {
            System.out.println("va a mover");
            boolean movido = ficha.validarMovimiento(
                    casillaInicio, casillaFin, 
                    this.color, tablero);
            if (movido) {
                return true;
            }
        }
        System.out.println("jugador no jugo");
        return false;     
    }

    public void setAjedrez(Ajedrez ajedrez) {
        this.ajedrez = ajedrez;
    }

    public String getNombre() {
        return this.nombre;
    }

    private void rendirse() {
        // No me gusta pero los estudiantes lo pidieron
        ajedrez.rendirse();
    }

}

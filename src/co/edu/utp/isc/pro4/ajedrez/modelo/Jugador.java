
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

    public boolean jugar(Casilla casillaInicio, Casilla casillaFin, 
            Ficha ficha, Tablero tablero, Ajedrez juego) {
        if (this.color == ficha.getColor()) {                            //si color jugador y color ficha son iguales
            if (casillaFin.isOcupada()) {
                if (this.color == casillaFin.getFicha().getColor()) {    //si el color de la ficha en casilla final es igual al del jugador
                    System.out.println("Fichas posI y posF mismo color");
                    return false;
                }
            }
            boolean movido = ficha.validarMovimiento(juego,
                    casillaInicio, casillaFin, 
                    this.color, tablero, true);
            if (movido) {
                return true;
            }
        }
//        System.out.println("jugador no jugo");
        return false;     
    }

    public void setAjedrez(Ajedrez ajedrez) {
        this.ajedrez = ajedrez;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Color getColor() {
        return color;
    }
}


package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Dibujable;
import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;


public abstract class Ficha extends Dibujable {

    private Casilla casilla;
    private final Color color;

    public Ficha(Color color) {
        this.color = color;
    }

    public abstract boolean validarMovimiento(Ajedrez juego, 
            Casilla casillaInicio, Casilla casillaFin, 
            Color color, Tablero tablero, boolean ejecutar);
    
    public boolean mover(Ajedrez juego, Casilla casillaInicio, 
            Casilla casillaFin, Color colorRey){
        this.setCasilla(null);
        casillaInicio.setFicha(null);
        this.setCasilla(casillaFin);
        casillaFin.setFicha(this);
        
        boolean movLegal = juego.reySeguro(color);
        if (!movLegal) {                    //revierte el mov si es ilegal
            this.setCasilla(null);
            casillaFin.setFicha(null);
            this.setCasilla(casillaInicio);
            casillaInicio.setFicha(this);
            return false;
        }
        return true;
    }

    public boolean comer(Ajedrez juego, Casilla casillaInicio, 
            Casilla casillaFin, Color color) {
        Ficha fichaCapturada = casillaFin.getFicha();
        
        fichaCapturada.setCasilla(null);
        casillaFin.setFicha(null);
        
        this.setCasilla(null);
        casillaInicio.setFicha(null);
        
        this.setCasilla(casillaFin);
        casillaFin.setFicha(this);
        
        boolean movLegal = juego.reySeguro(color);
        if (!movLegal) {                             // revierte el mov si es ilegal
            this.setCasilla(null);                   // pone en nulls la final
            casillaFin.setFicha(null);
            
            this.setCasilla(casillaInicio);          // mueve la ficha a casilla inicio
            casillaInicio.setFicha(this);
            
            fichaCapturada.setCasilla(casillaFin);   // la ficha q captuar es regresada a casilla fin
            casillaFin.setFicha(fichaCapturada);
            return false;
        }
        return true;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        String tipo = "";
        if (this instanceof Peon) {
            tipo = "P";
        } else if (this instanceof Torre) {
            tipo = "T";
        } else if (this instanceof Caballo) {
            tipo = "C";
        } else if (this instanceof Alfil) {
            tipo = "A";
        } else if (this instanceof Reina) {
            tipo = "D";
        } else if (this instanceof Rey) {
            tipo = "R";
        }
        return tipo + (getColor() == Color.BLANCO ? "B" : "N");
    }
}


package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Reina extends Ficha {

    public Reina(Color color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        int colInicio = casillaInicio.getColumna() - 'A';
        int filaInicio = casillaInicio.getFila() - 1;
        
        int colFin = casillaFin.getColumna() - 'A';
        int filaFin = casillaFin.getFila() - 1;
        
        Casilla casillaIntermedia = null;
        
        if (colInicio == colFin) {              //misma columna
            if (filaInicio  > filaFin) {        //mueve arriba
                for (int i = filaInicio-1; i >= filaFin; i--) {
                    casillaIntermedia = tablero.getCasilla(i, colFin);
                    if (casillaIntermedia != casillaFin
                            && casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            } else if (filaInicio < filaFin) {  //mueve abajo
                for (int i = filaInicio+1; i <= filaFin; i++) {
                    casillaIntermedia = tablero.getCasilla(i, colFin);
                    if (casillaIntermedia != casillaFin 
                            && casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            }
        } else if (filaInicio == filaFin) {     //misma fila
            if (colInicio > colFin) {           //mueve izquierda
                for (int j = colInicio-1; j >= colFin; j--) {
                    casillaIntermedia = tablero.getCasilla(filaFin, j);
                    if (casillaIntermedia != casillaFin 
                            && casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            } else if (colInicio < colFin) {    //mueve derecha
                for (int j = colInicio+1; j <= colFin; j++) {
                    casillaIntermedia = tablero.getCasilla(filaFin, j);
                    if (casillaIntermedia != casillaFin 
                            && casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            }
        } else if (colInicio > colFin && filaInicio > filaFin) {           //noroeste
            for (int i = filaInicio-1, j = colInicio-1; 
                    i >= filaFin && j >= colFin;
                    i--, j--) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia != casillaFin 
                        && casillaIntermedia.isOcupada()) {
                    return false;
                }
            }
        } else if (colInicio > colFin && filaInicio < filaFin) {    //suroeste
            for (int i = filaInicio+1, j = colInicio-1; 
                    i <= filaFin && j >= colFin;
                    i++, j--) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia != casillaFin 
                        && casillaIntermedia.isOcupada()) {
                    return false;
                }
            }
        } else if (colInicio < colFin && filaInicio > filaFin) {    //noreste
            for (int i = filaInicio-1, j = colInicio+1; 
                    i >= filaFin && j <= colFin;
                    i--, j++) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia != casillaFin 
                        && casillaIntermedia.isOcupada()) {
                    return false;
                }
            }
        } else if (colInicio < colFin && filaInicio < filaFin) {    //sureste
            for (int i = filaInicio+1, j = colInicio+1; 
                    i <= filaFin && j <= colFin;
                    i++, j++) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia != casillaFin 
                        && casillaIntermedia.isOcupada()) {
                    return false;
                }
            }
        }
        if (casillaIntermedia == casillaFin) {
            if (casillaFin.isOcupada()) {
                this.comer();
                return true;
            }
            this.mover(casillaInicio, casillaFin);
            return true;
        }
        return false;
    }

    @Override
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g, float x, float y) {
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,    //color de la ficha
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(new Ellipse2D.Float(x + 17, y + 1, 15, 15));
        g.fill(new Rectangle2D.Float(x + 15, y + 15, 20, 25));
        g.fill(new Rectangle2D.Float(x + 10, y + 40, 30, 5));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 17, y + 1, 15, 15));
        g.draw(new Rectangle2D.Float(x + 15, y + 15, 20, 25));
        g.draw(new Rectangle2D.Float(x + 10, y + 40, 30, 5));
    }
}
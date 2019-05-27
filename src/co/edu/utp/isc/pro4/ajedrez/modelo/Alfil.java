
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;


public class Alfil extends Ficha {

    public Alfil(Color color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(Ajedrez juego, 
            Casilla casillaInicio, Casilla casillaFin, 
            Color color, Tablero tablero, boolean ejecutar) {
        int colInicio = casillaInicio.getColumna() - 'A';
        int filaInicio = casillaInicio.getFila() - 1;
        
        int colFin = casillaFin.getColumna() - 'A';
        int filaFin = casillaFin.getFila() - 1;
        
        Casilla casillaIntermedia = null;
        
        if (colInicio > colFin && filaInicio > filaFin) {           //noroeste
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
            if (!ejecutar) {
                return true;
            }
            if (casillaFin.isOcupada()) {
                return this.comer(juego, casillaInicio, casillaFin, color);
            }
            return this.mover(juego, casillaInicio, casillaFin, color);
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 22, y + 20);
        polyline.lineTo(x + 15, y + 45);
        polyline.lineTo(x + 35, y + 45);
        polyline.lineTo(x + 28, y + 20);
        polyline.lineTo(x + 22, y + 20);
        
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,    //color de la ficha
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(new Ellipse2D.Float(x + 18, y + 7, 13, 13));
        g.fill(polyline);
        
        g.setColor(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 18, y + 7, 13, 13));
        g.draw(polyline);
    } 
}
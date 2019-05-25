
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;


public class Alfil extends Ficha {

    public Alfil(Color color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        int colInicio = casillaInicio.getColumna() - 'A';
        int filaInicio = casillaInicio.getFila() - 1;
        
        int colFin = casillaFin.getColumna() - 'A';
        int filaFin = casillaFin.getFila() - 1;
        
        Casilla casillaIntermedia = null;
        
        if (colInicio > colFin && filaInicio > filaFin) {
            System.out.println("noroeste");
            for (int i = filaInicio-1, j = colInicio-1; 
                    i >= filaFin && j >= colFin;
                    i--, j--) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia.isOcupada()) {
                        return false;
                }
            }
        } else if (colInicio > colFin && filaInicio < filaFin) {
                System.out.println("suroeste");
            for (int i = filaInicio+1, j = colInicio-1; 
                    i <= filaFin && j >= colFin;
                    i++, j--) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia.isOcupada()) {
                        return false;
                }
            }
        } else if (colInicio < colFin && filaInicio > filaFin) {
            System.out.println("noreste");
            for (int i = filaInicio-1, j = colInicio+1; 
                    i >= filaFin && j <= colFin;
                    i--, j++) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia.isOcupada()) {
                        return false;
                }
            }
        } else if (colInicio < colFin && filaInicio < filaFin) {
            System.out.println("sureste");
            for (int i = filaInicio+1, j = colInicio+1; 
                    i <= filaFin && j <= colFin;
                    i++, j++) {
                casillaIntermedia = tablero.getCasilla(i, j);
                if (casillaIntermedia.isOcupada()) {
                        return false;
                }
                System.out.println("sale");
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

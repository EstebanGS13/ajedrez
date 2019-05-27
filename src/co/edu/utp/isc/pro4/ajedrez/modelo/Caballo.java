
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;


public class Caballo extends Ficha {

    public Caballo(Color color) {
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
        
        if (colInicio == colFin || filaInicio == filaFin) {     //si casilla final esta en la misma fila o misma columna
            return false;
        }
        
        int i = filaInicio;
        int j = colInicio;
        boolean encontrado = false;
        
        if (filaInicio > filaFin) {
            if (colInicio > colFin) {
                if (filaFin == i-1 && colFin == j-2) {
                    encontrado = true;
                } else if (filaFin == i-2 && colFin == j-1) {
                    encontrado = true;
                }
            } else if (colInicio < colFin) {
                if (filaFin == i-1 && colFin == j+2) {
                    encontrado = true;
                } else if (filaFin == i-2 && colFin == j+1) {
                    encontrado = true;
                }
            }
        } else if (filaInicio < filaFin) {
            if (colInicio > colFin) {
                if (filaFin == i+1 && colFin == j-2) {
                    encontrado = true;
                } else if (filaFin == i+2 && colFin == j-1) {
                    encontrado = true;
                }
            } else if (colInicio < colFin) {
                if (filaFin == i+1 && colFin == j+2) {
                    encontrado = true;
                } else if (filaFin == i+2 && colFin == j+1) {
                    encontrado = true;
                }
            }
        }
        if (encontrado) {
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
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 13);
        polyline.moveTo(x + 10, y + 42);
        polyline.lineTo(x + 26, y + 25);
        polyline.lineTo(x + 19, y + 22);
        polyline.lineTo(x + 16, y + 25);
        polyline.lineTo(x + 13, y + 25);
        polyline.lineTo(x + 10, y + 19);
        polyline.lineTo(x + 24, y + 8);
        polyline.lineTo(x + 24, y + 5);
        polyline.lineTo(x + 26, y + 5);
        polyline.lineTo(x + 38, y + 21);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 12, y + 45);
        polyline.lineTo(x + 10, y + 42);
        
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 100, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);

        g.setColor(java.awt.Color.BLACK);
        g.draw(polyline);
    }
}
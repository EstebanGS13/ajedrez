
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;


public class Torre extends Ficha {

    public Torre(Color color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        char colInicio = casillaInicio.getColumna();
        int filaInicio = casillaInicio.getFila();
        
        char colFin = casillaFin.getColumna();
        int filaFin = casillaFin.getFila();
        System.out.println(colInicio);
        System.out.println(filaInicio);
        System.out.println(colFin);
        
        System.out.println(filaFin);
        Casilla casillaIntermedia;

        if (colInicio == colFin) {              //misma columna
            if (filaInicio  > filaFin) {        //mueve arriba
                for (int i = filaInicio-1; i >= filaFin; i--) {
                    casillaIntermedia = tablero.getCasilla(i, colFin);
                    if (casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            } else if (filaInicio < filaFin) {  //mueve abajo
                for (int i = filaInicio+1; i <= filaFin; i++) {
                    casillaIntermedia = tablero.getCasilla(i, colFin);
                    System.out.println(casillaIntermedia.getFicha());
                    if (casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            }
        } else if (filaInicio == filaFin) {     //misma fila
            if (colInicio > colFin) {           //mueve izquierda
                for (int i = colInicio-1; i >= colFin; i--) {
                    casillaIntermedia = tablero.getCasilla(i, colFin);
                    if (casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            } else if (colInicio < colFin) {    //mueve derecha
                for (int i = colInicio+1; i <= colFin; i++) {
                    casillaIntermedia = tablero.getCasilla(i, colFin);
                    if (casillaIntermedia.isOcupada()) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        this.mover(casillaInicio, casillaFin);
        return true;
    }
    
    @Override
    public void mover(Casilla casillaInicio, Casilla casillaFin) {
        //TODO: validar el mov de la torre
        this.setCasilla(null);
        this.setCasilla(casillaFin);
        casillaFin.setFicha(this);
        casillaInicio.setFicha(null);
        
    }

    
    @Override
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void enroque() {
        
    }
    
    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 5, y + 5);
        polyline.lineTo(x + 5, y + 15);
        polyline.lineTo(x + 10, y + 15);
        polyline.lineTo(x + 10, y + 45);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 40, y + 15);
        polyline.lineTo(x + 45, y + 15);
        polyline.lineTo(x + 45, y + 5);
        polyline.lineTo(x + 37, y + 5);
        polyline.lineTo(x + 37, y + 10);
        polyline.lineTo(x + 29, y + 10);
        polyline.lineTo(x + 29, y + 5);
        polyline.lineTo(x + 21, y + 5);
        polyline.lineTo(x + 21, y + 10);
        polyline.lineTo(x + 13, y + 10);
        polyline.lineTo(x + 13, y + 5);
        polyline.lineTo(x + 5, y + 5);

        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 100, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);

        g.setColor(java.awt.Color.BLACK);
        g.draw(polyline);
    }  
}

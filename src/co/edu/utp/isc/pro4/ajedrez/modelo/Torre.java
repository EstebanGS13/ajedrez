
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
    public void mover(Casilla casillaInicio, Casilla casillaFin) {
        this.setCasilla(null);
        this.setCasilla(casillaFin);
        casillaFin.setFicha(this);
        casillaInicio.setFicha(null);   
    }

    
    @Override
    public void comer() {
        System.out.println("come...");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void enroque() {
        //TODO: mov del enroque
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

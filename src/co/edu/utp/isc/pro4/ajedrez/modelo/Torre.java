
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;


public class Torre extends Ficha {

    private boolean primerMov;
    
    public Torre(Color color) {
        super(color);
        primerMov = true;
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
            if (!ejecutar) {            //si solo se quiere evaluar el movimiento
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
    public boolean mover(Ajedrez juego, Casilla casillaInicio, 
            Casilla casillaFin, Color color) {
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
        this.primerMov = false;
        return true;
    }
    
    @Override
    public boolean comer(Ajedrez juego, Casilla casillaInicio, 
            Casilla casillaFin, Color colorRey) {
        Ficha fichaCapturada = casillaFin.getFicha();
        
        fichaCapturada.setCasilla(null);
        casillaFin.setFicha(null);
        
        this.setCasilla(null);
        casillaInicio.setFicha(null);
        
        this.setCasilla(casillaFin);
        casillaFin.setFicha(this);
        
        boolean movLegal = juego.reySeguro(colorRey);
        if (!movLegal) {                             // revierte el mov si es ilegal
            this.setCasilla(null);                   // pone en nulls la final
            casillaFin.setFicha(null);
            
            this.setCasilla(casillaInicio);          // mueve la ficha a casilla inicio
            casillaInicio.setFicha(this);
            
            fichaCapturada.setCasilla(casillaFin);   // la ficha q captuar es regresada a casilla fin
            casillaFin.setFicha(fichaCapturada);
            return false;
        }
        this.primerMov = false;
        return true;
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
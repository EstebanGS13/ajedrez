
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Peon extends Ficha {
    
    private boolean primerMov;

    public Peon(Color color) {
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
        
        if (colFin < colInicio-1 || colFin > colInicio+1) {
            return false;
        }
        
        int i = filaInicio;
        int j = colInicio;
        boolean encontrado = false;
        boolean come = false;
        Casilla intermedia = null;
        if (colFin == colInicio) {
            if (color == Color.BLANCO) {
                if ((filaFin == i+1) && (!casillaFin.isOcupada())) {
                    encontrado = true;
                } else if ((filaFin == i+2) && primerMov
                       && (!casillaFin.isOcupada())) {
                    intermedia = tablero.getCasilla(i+1, colFin);
                    if (!intermedia.isOcupada()){
                        encontrado = true;
                    }    
                }
            } else if (color == Color.NEGRO) {
                if ((filaFin == i-1) && (!casillaFin.isOcupada())) {
                    encontrado = true;
                } else if ((filaFin == i-2) && primerMov
                        && (!casillaFin.isOcupada())) {
                    intermedia = tablero.getCasilla(i-1, colFin);
                    if (!intermedia.isOcupada()){
                        encontrado = true;
                    }    
                }
            }
        } else if ((colFin == j-1) || (colFin == j+1)) {
            if ((filaFin == i+1) && casillaFin.isOcupada()) {
                come = true;
            } else if ((filaFin == i-1) && casillaFin.isOcupada()) {
                come = true;
            }
        }
        if (encontrado) {
            if (!ejecutar) {
                return true;
            }
            return this.mover(juego, casillaInicio, casillaFin, color);
        } else if (come) {
            if (!ejecutar) {
                return true;
            }
            return this.comer(juego, casillaInicio, casillaFin, color);
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
        this.primerMov = false;
        return true;
    }

    public void promocion(){
        //TODO: convertir al peon una vez llega al otro extremo del tablero en cualquier ficha excepto rey y peon
    }
    @Override
    public void draw(Graphics2D g, float x, float y) {
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(new Ellipse2D.Float(x + 17, y + 15, 16, 16));
        g.fill(new Rectangle2D.Float(x + 15, y + 30, 20, 15));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 17, y + 15, 16, 16));
        g.draw(new Rectangle2D.Float(x + 15, y + 30, 20, 15));
    }
}
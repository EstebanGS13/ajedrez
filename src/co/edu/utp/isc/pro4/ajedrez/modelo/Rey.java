
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;


public class Rey extends Ficha {
    
    private boolean primerMov;

    public Rey(Color color) {
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
        
        // Validacion si el movimiento es enroque
        if ((colFin == colInicio+2) && (filaFin == filaInicio)
                && primerMov) {
            return this.enroqueCorto(juego, casillaInicio, casillaFin, color, tablero);
        } else if ((colFin == colInicio-2) && (filaFin == filaInicio)
                && primerMov) {
            return this.enroqueLargo(juego, casillaInicio, casillaFin, color, tablero);
        } 
        boolean encontrado = false;
        
        if (filaFin == filaInicio-1) {
            if (colFin == colInicio-1) {
                encontrado = true;
            } else if (colFin == colInicio) {
                encontrado = true;
            } else if (colFin == colInicio+1) {
                encontrado = true;
            }
        } else if (filaFin == filaInicio) {
            if (colFin == colInicio-1) {
                encontrado = true;
            } else if (colFin == colInicio+1) {
                encontrado = true;
            }
        } else if (filaFin == filaInicio+1) {
            if (colFin == colInicio-1) {
                encontrado = true;
            } else if (colFin == colInicio) {
                encontrado = true;
            } else if (colFin == colInicio+1) {
                encontrado = true;
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

    public boolean enroqueCorto(Ajedrez juego, Casilla reyCasillaInicio, 
            Casilla reyCasillaFin, Color colorRey, Tablero tablero) {
        
        if (juego.reySeguro(colorRey)) {
            int i = reyCasillaInicio.getFila() - 1;
            int j = reyCasillaInicio.getColumna() - 'A';
            Casilla torreInicio = tablero.getCasilla(i, j+3);
            Casilla torreFin = tablero.getCasilla(i, j+1);
            
            Ficha torreTomada = torreInicio.getFicha();
            
            if ((torreTomada instanceof Torre) && (torreTomada.getColor() == colorRey)){
                if (((Torre) torreTomada).isPrimerMov()) {                                               // si la torre no se ha movido por primera vez
                    if (((Torre) torreTomada).validarMovimiento(
                            juego, torreInicio, torreFin, colorRey, tablero, false)) {                   // si la torre se puede mover al lugar de enroque
                        if (this.mover(juego, reyCasillaInicio, reyCasillaFin, colorRey)) {              // si puede mover el rey al lugar de enroque, lo hace
                            return ((Torre) torreTomada).mover(juego, torreInicio, torreFin, colorRey);  // si se movio el rey, mueve la torre
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean enroqueLargo(Ajedrez juego, Casilla reyCasillaInicio, 
            Casilla reyCasillaFin, Color colorRey, Tablero tablero) {
        
        if (juego.reySeguro(colorRey)) {
            int i = reyCasillaInicio.getFila() - 1;
            int j = reyCasillaInicio.getColumna() - 'A';
            Casilla torreInicio = tablero.getCasilla(i, j-4);
            Casilla torreFin = tablero.getCasilla(i, j-1);
            
            Ficha torreTomada = torreInicio.getFicha();
            
            if ((torreTomada instanceof Torre) && (torreTomada.getColor() == colorRey)){
                if (((Torre) torreTomada).isPrimerMov()) {
                    if (((Torre) torreTomada).validarMovimiento(
                            juego, torreInicio, torreFin, colorRey, tablero, false)) {
                        if (this.mover(juego, reyCasillaInicio, reyCasillaFin, colorRey)) {
                            return ((Torre) torreTomada).mover(juego, torreInicio, torreFin, colorRey);
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 18, y + 6);
        polyline.lineTo(x + 18, y + 10);
        polyline.lineTo(x + 23, y + 10);
        polyline.lineTo(x + 23, y + 15);
        polyline.lineTo(x + 27, y + 15);
        polyline.lineTo(x + 27, y + 10);
        polyline.lineTo(x + 32, y + 10);
        polyline.lineTo(x + 32, y + 6);
        polyline.lineTo(x + 27, y + 6);
        polyline.lineTo(x + 27, y + 2);
        polyline.lineTo(x + 23, y + 2);
        polyline.lineTo(x + 23, y + 6);
        polyline.lineTo(x + 18, y + 6);
       
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,    //color de la ficha
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);
        g.fill(new Rectangle2D.Float(x + 15, y + 15, 20, 25));
        g.fill(new Rectangle2D.Float(x + 10, y + 40, 30, 5));
        
        g.setPaint(java.awt.Color.BLACK);
        g.draw(polyline);
        g.draw(new Rectangle2D.Float(x + 15, y + 15, 20, 25));
        g.draw(new Rectangle2D.Float(x + 10, y + 40, 30, 5));
    }
}

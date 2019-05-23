
package co.edu.utp.isc.pro4.ajedrez.modelo;

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
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        char colInicio = casillaInicio.getColumna();
        int filaInicio = casillaInicio.getFila();
        
        char colFin = casillaFin.getColumna();
        int filaFin = casillaFin.getFila();
        
        if (color == Color.BLANCO){
            if (colInicio == colFin && filaInicio < filaFin) {
                if (primerMov) {                                    //si se va a mover por primera vez
                    if (filaFin == (filaInicio+2)) { 
                        String posIntermedia = colInicio + Integer.toString(filaInicio+1);
                        Casilla casillaIntermedia = tablero.getCasilla(posIntermedia);
                        if (!casillaIntermedia.isOcupada()
                                && !casillaFin.isOcupada()) {       //si la casilla intermedia y final estan libres
                            this.mover(casillaInicio, casillaFin);
                            primerMov = false;
                            return true;
                        }
                    } else if (filaFin == (filaInicio+1)){          //si al inicio solo se mueve 1 casilla
                        if (!casillaFin.isOcupada()) {
                            this.mover(casillaInicio, casillaFin);
                            primerMov = false;
                            return true;
                        }
                    }
                } else {
                    //mov luego del primer mov...
                    if (filaFin == (filaInicio+1)) {
                        this.mover(casillaInicio, casillaFin);
                        return true;
                    }
                }
            } else if(colFin < colInicio || colFin > colInicio){
                //TODO: validar cuando come
                System.out.println("come...");
                this.comer();
                return true;
            }
        }
        else if (color == Color.NEGRO) {
            if (colInicio == colFin && filaInicio > filaFin) {      //si la columna es la misma y fila es menor
                if (primerMov) {                                    //si se va a mover por primera vez
                    if (filaFin == (filaInicio-2)) {                //si la casilla dada coincide con su posible movimiento inicial de 2 casillas
                        String posIntermedia = colInicio + Integer.toString(filaInicio-1);
                        Casilla casillaIntermedia = tablero.getCasilla(posIntermedia);
                        if (!casillaIntermedia.isOcupada()
                                && !casillaFin.isOcupada()) {       //si la casilla intermedia y final estan libres
                            this.mover(casillaInicio, casillaFin);
                            primerMov = false;
                            return true;
                        }
                    } else if (filaFin == (filaInicio-1)){
                        if (!casillaFin.isOcupada()) {
                            this.mover(casillaInicio, casillaFin);
                            primerMov = false;
                            return true;
                        }
                    }
                } else {
                    //mov luego del primer mov...
                    if (filaFin == (filaInicio-1)) {
                        this.mover(casillaInicio, casillaFin);
                        return true;
                    }
                }
            } else if(colFin < colInicio || colFin > colInicio){
                //TODO: validar cuando come
                System.out.println("come...");
                this.comer();
                return true;
            } 
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
        //TODO: Comer como peon
    }

    public void promocion(){
        //TODO: convertir al peon una vez llega al otro extremo del tablero en cualquier ficha excepto rey y peon
    }
    @Override
    public void draw(Graphics2D g, float x, float y) {
        // 50x50 dibujar la ficha
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

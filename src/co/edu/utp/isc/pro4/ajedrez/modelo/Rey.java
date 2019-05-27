
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
    public boolean validarMovimiento(
            Ajedrez juego, Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero, boolean ejecutar) {
        int colInicio = casillaInicio.getColumna() - 'A';
        int filaInicio = casillaInicio.getFila() - 1;
        
        int colFin = casillaFin.getColumna() - 'A';
        int filaFin = casillaFin.getFila() - 1;
        //TODO: implementar el mov del rey
        if (primerMov) {
            //si mueve dos+ casillas... si enoque...
        } else {
            
        }
        
        //this.mover(casillaInicio, casillaFin);
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

    public void enroque() {
        //TODO: mov del enroque
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

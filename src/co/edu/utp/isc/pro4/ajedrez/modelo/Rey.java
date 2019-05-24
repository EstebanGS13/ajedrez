
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;


public class Rey extends Ficha {

    public Rey(Color color) {
        super(color);
    }


    @Override
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        this.mover(casillaInicio, casillaFin);
        return true;
    }
}

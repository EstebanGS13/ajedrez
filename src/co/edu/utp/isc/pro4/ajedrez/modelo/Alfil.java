
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

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        this.mover(casillaInicio, casillaFin);
        return true;
    }
}

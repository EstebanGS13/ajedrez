
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Reina extends Ficha {

    public Reina(Color color) {
        super(color);
    }


    @Override
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g, float x, float y) {
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,    //color de la ficha
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(new Ellipse2D.Float(x + 17, y + 1, 15, 15));
        g.fill(new Rectangle2D.Float(x + 15, y + 15, 20, 25));
        g.fill(new Rectangle2D.Float(x + 10, y + 40, 30, 5));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 17, y + 1, 15, 15));
        g.draw(new Rectangle2D.Float(x + 15, y + 15, 20, 25));
        g.draw(new Rectangle2D.Float(x + 10, y + 40, 30, 5));
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        this.mover(casillaInicio, casillaFin);
        return true;
    }
}

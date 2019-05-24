
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;


public class Caballo extends Ficha {

    public Caballo(Color color) {
        super(color);
    }



    @Override
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 13);
        polyline.moveTo(x + 10, y + 42);
        polyline.lineTo(x + 26, y + 25);
        polyline.lineTo(x + 19, y + 22);
        polyline.lineTo(x + 16, y + 25);
        polyline.lineTo(x + 13, y + 25);
        polyline.lineTo(x + 10, y + 19);
        polyline.lineTo(x + 24, y + 8);
        polyline.lineTo(x + 24, y + 5);
        polyline.lineTo(x + 26, y + 5);
        polyline.lineTo(x + 38, y + 21);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 12, y + 45);
        polyline.lineTo(x + 10, y + 42);
        
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 100, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);

        g.setColor(java.awt.Color.BLACK);
        g.draw(polyline);
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicio, Casilla casillaFin, Color color, Tablero tablero) {
        this.mover(casillaInicio, casillaFin);
        return true;
    }
}

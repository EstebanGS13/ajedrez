
package co.edu.utp.isc.pro4.ajedrez.ui;

import co.edu.utp.isc.pro4.ajedrez.modelo.Tablero;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class PnlTablero extends JPanel {

    private Tablero tablero;
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("Pintando Objetos");
        if (tablero == null) {
            super.paint(g);
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero.getCasilla(i, j)
                        .draw(g2, j * 50, (7-i) * 50);      // imprime la casilla en restado 7 de la pos x
            }
        }
    }
}

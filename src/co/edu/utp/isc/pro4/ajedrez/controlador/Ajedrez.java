
package co.edu.utp.isc.pro4.ajedrez.controlador;

import co.edu.utp.isc.pro4.ajedrez.modelo.Alfil;
import co.edu.utp.isc.pro4.ajedrez.modelo.Caballo;
import co.edu.utp.isc.pro4.ajedrez.modelo.Casilla;
import co.edu.utp.isc.pro4.ajedrez.modelo.Color;
import co.edu.utp.isc.pro4.ajedrez.modelo.Cronometro;
import co.edu.utp.isc.pro4.ajedrez.modelo.Ficha;
import co.edu.utp.isc.pro4.ajedrez.modelo.Jugador;
import co.edu.utp.isc.pro4.ajedrez.modelo.Peon;
import co.edu.utp.isc.pro4.ajedrez.modelo.Reina;
import co.edu.utp.isc.pro4.ajedrez.modelo.Rey;
import co.edu.utp.isc.pro4.ajedrez.modelo.Tablero;
import co.edu.utp.isc.pro4.ajedrez.modelo.Torre;
import co.edu.utp.isc.pro4.ajedrez.ui.PnlTablero;


public class Ajedrez {

    private PnlTablero pnlTablero;
    private Jugador[] jugadores;
    private Tablero tablero;
    private Cronometro cronometro;
    private String[] posReyes;
    
    private int turno;    

    public Ajedrez() {
        jugadores = new Jugador[2];
        tablero = new Tablero();
        cronometro = new Cronometro();
        posReyes = new String[2];
        turno = 0;
    }

    public Ajedrez(Jugador jugador1, Jugador jugador2) {
        this();
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
    }

    public void setPosReyes(int turno, String nuevaPos) {
        this.posReyes[turno] = nuevaPos;
    }
    
    public PnlTablero getPnlTablero() {
        return pnlTablero;
    }

    public void setPnlTablero(PnlTablero pnlTablero) {
        this.pnlTablero = pnlTablero;
        pnlTablero.setTablero(tablero);
    }

    public void jugar() {
        jugadores[0].setAjedrez(this);
        jugadores[1].setAjedrez(this);
        posReyes[0] = "E1";
        posReyes[1] = "E8";
        ubicarFichasTablero();
        
        cronometro.iniciar();
        mostrarTablero();
    }

    public int validarPosiciones(Posicion posClicks) {
        String posInicial = posClicks.getPosicionUno();
        String posFinal = posClicks.getPosicionDos();
        Casilla casillaInicio = this.tablero.getCasilla(posInicial);
        Casilla casillaFin = this.tablero.getCasilla(posFinal);
        
        if (casillaInicio.isOcupada()) {
            Ficha fichaTomada = casillaInicio.getFicha();
            boolean valido = jugadores[turno].jugar(
                    casillaInicio, casillaFin, 
                    fichaTomada, this.tablero, this);
            if (valido) {
                if (fichaTomada instanceof Rey) {
                    int fila = casillaFin.getFila();
                    char col = casillaFin.getColumna();
                    String nuevaPos = (col) + Integer.toString(fila);
                    posReyes[turno] = nuevaPos;
                }
                mostrarTablero();
                this.cambioTurno();
                boolean enJaque = validarJaque(jugadores[turno].getColor());
                return this.turno;
            } else {
                System.out.println("Mov no valido");
            }
        } else{
            System.out.println("no hay nada");
        }
        return this.turno;
    }
    
    public void cambioTurno() {
        turno = (turno == 0 ? 1 : 0);
        cronometro.cambio();
    }

    public boolean reySeguro(Color color) {
        int turnoRey = (color == Color.BLANCO) ? 0 : 1;
        Casilla reyUbicacion = tablero.getCasilla(posReyes[turnoRey]);
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                Ficha fichaEvaluada = casilla.getFicha();
                if (casilla.isOcupada() 
                        && fichaEvaluada.getColor() != color) {
                    boolean amenaza = fichaEvaluada.validarMovimiento(
                            this, casilla, reyUbicacion, color, tablero, false);
                    if (amenaza) {
                        System.out.println("Rey " + color +" amenazado");
                        return false;   //significa que no esta a salvo
                    }
                }
            }
        }
        System.out.println("Rey " + color +" a salvo");
        return true;
    }
    
    private boolean reySalvable(Color colorRey) {
        Tablero copiaTablero = new Tablero();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Casilla casillaTomada = copiaTablero.getCasilla(i, j);
                Ficha fichaTomada = casillaTomada.getFicha();
                if (casillaTomada.isOcupada()) {
                    if (fichaTomada.getColor() != colorRey) {
                        continue;
                    }
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            Casilla casillaFin = copiaTablero.getCasilla(k, l);
                            if (casillaTomada != casillaFin) {
                                if (casillaFin.isOcupada() 
                                        && (casillaFin.getFicha().getColor() 
                                        != fichaTomada.getColor())) {
                                    continue;
                                }
                                boolean puedeMover = fichaTomada.validarMovimiento(
                                        this, casillaTomada, casillaFin, 
                                        colorRey, copiaTablero, true);
                                if (puedeMover) {
                                    return true;
                                }
                            }      
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean validarJaque(Color colorRey) {
        
        boolean safe = reySeguro(colorRey);
        if (!safe) {
            if (validarJaqueMate(colorRey)) {
                System.out.println("Ha perdido");
            }
        }
        return false;
    }
    
    private boolean validarJaqueMate(Color colorRey) {
        if (reySalvable(colorRey)) {
            return false;
        }
        System.out.println("jaqueMate");
        return true;
    }

    private boolean validarTablas() {
        //TODO: Validar si los jugadores se han quedado sin posibilidad de ganar
        return false;
    }

    private void ubicarFichasTablero() {
        asociarFichaTablero(tablero.getCasilla("A1"), new Torre(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("B1"), new Caballo(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("C1"), new Alfil(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("D1"), new Reina(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("E1"), new Rey(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("F1"), new Alfil(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("G1"), new Caballo(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("H1"), new Torre(Color.BLANCO));

        asociarFichaTablero(tablero.getCasilla("A8"), new Torre(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("B8"), new Caballo(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("C8"), new Alfil(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("D8"), new Reina(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("E8"), new Rey(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("F8"), new Alfil(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("G8"), new Caballo(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("H8"), new Torre(Color.NEGRO));

        for (int i = 0; i < 8; i++) {
            asociarFichaTablero(tablero.getCasilla(1, i), new Peon(Color.BLANCO));
            asociarFichaTablero(tablero.getCasilla(6, i), new Peon(Color.NEGRO));
        }
    }

    private void asociarFichaTablero(Casilla c, Ficha f) {
        f.setCasilla(c);
        c.setFicha(f);
    }
    
    private void mostrarTablero() {
        pnlTablero.updateUI();
//        System.out.println("  \tA \tB \tC \tD \tE \tF \tG \tH");
//        for (int i = 0; i < 8; i++) {
//            System.out.print((i + 1));
//            for (int j = 0; j < 8; j++) {
//                System.out.print("\t" + tablero.getCasilla(i, j));
//            }
//            System.out.println();
//        }
    }
}

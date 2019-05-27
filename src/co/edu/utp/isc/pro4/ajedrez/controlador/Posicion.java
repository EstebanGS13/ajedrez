
package co.edu.utp.isc.pro4.ajedrez.controlador;


public class Posicion {
    
    private String posicionUno;
    private String posicionDos;

    public Posicion() {
        this.posicionUno = "";
        this.posicionDos = "";
    }

    public String getPosicionUno() {
        return posicionUno;
    }

    public String getPosicionDos() {
        return posicionDos;
    }

    public void setPosicionUno(String posicionUno) {
        this.posicionUno = posicionUno;
    }

    public void setPosicionDos(String posicionDos) {
        this.posicionDos = posicionDos;
    }
}

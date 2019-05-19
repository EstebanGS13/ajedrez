
package co.edu.utp.isc.pro4.ajedrez.controlador;


public class Posicion {
    
    private String posicionInicio;
    private String posicionFin;

    public Posicion() {
        this.posicionInicio = "";
        this.posicionFin = "";
    }

    public String getPosicionInicio() {
        return posicionInicio;
    }

    public String getPosicionFin() {
        return posicionFin;
    }

    public void setPosicionInicio(String posicionInicio) {
        this.posicionInicio = posicionInicio;
    }

    public void setPosicionFin(String posicionFin) {
        this.posicionFin = posicionFin;
    }
}

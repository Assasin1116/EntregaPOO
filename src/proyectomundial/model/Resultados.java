
package proyectomundial.model;

public class Resultados {
     String grupo;
     String local;
     String visitante;
     String continente_local;
     String continente_visitante;
     String goles_locales;
     String goles_visitante;
     String equipostotales;
     String seleccion;
     int totalGoles;
     String equipo;
     int puntos;

    public Resultados(String grupo, String equipo, int puntos) {
        this.grupo = grupo;
        this.equipo = equipo;
        this.puntos = puntos;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Resultados(String seleccion, int totalGoles) {
        this.seleccion = seleccion;
        this.totalGoles = totalGoles;
    }

    public String getEquipostotales() {
        return equipostotales;
    }

    public void setEquipostotales(String equipostotales) {
        this.equipostotales = equipostotales;
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public int getTotalGoles() {
        return totalGoles;
    }

    public void setTotalGoles(int totalGoles) {
        this.totalGoles = totalGoles;
    }
    public Resultados() {
    }

    public Resultados(String equipostotales) {
        this.equipostotales = equipostotales;
    }
    
    public Resultados(String grupo, String local, String visitante, String continente_local, String continente_visitante, String goles_locales, String goles_visitante) {
        this.grupo = grupo;
        this.local = local;
        this.visitante = visitante;
        this.continente_local = continente_local;
        this.continente_visitante = continente_visitante;
        this.goles_locales = goles_locales;
        this.goles_visitante = goles_visitante;
    }

     
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getContinente_local() {
        return continente_local;
    }

    public void setContinente_local(String continente_local) {
        this.continente_local = continente_local;
    }

    public String getContinente_visitante() {
        return continente_visitante;
    }

    public void setContinente_visitante(String continente_visitante) {
        this.continente_visitante = continente_visitante;
    }

    public String getGoles_locales() {
        return goles_locales;
    }

    public void setGoles_locales(String goles_locales) {
        this.goles_locales = goles_locales;
    }

    public String getGoles_visitante() {
        return goles_visitante;
    }

    public void setGoles_visitante(String goles_visitante) {
        this.goles_visitante = goles_visitante;
    }
     
}

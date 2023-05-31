/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.model;

/**
 *
 * @author miguelropero
 */
public class Seleccion {

    String nombre;
    String continente;
    String dt;
    String nacionalidad;
    String equipos_totales;
    int conteo;
    int cantidad_directores_tecnicos;
    int cantidad;
    int ranking;

    public Seleccion(String nacionalidad, int cantidad_directores_tecnicos, int ranking) {
        this.nacionalidad = nacionalidad;
        this.ranking = ranking;
        this.cantidad_directores_tecnicos = cantidad_directores_tecnicos;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad_directores_tecnicos() {
        return cantidad_directores_tecnicos;
    }

    public void setCantidad_directores_tecnicos(int cantidad_directores_tecnicos) {
        this.cantidad_directores_tecnicos = cantidad_directores_tecnicos;
    }

    public String getEquipos_totales() {
        return equipos_totales;
    }

    public void setEquipos_totales(String equipos_totales) {
        this.equipos_totales = equipos_totales;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public Seleccion() {
    }

    public Seleccion(String equipos_totales) {
        this.equipos_totales = equipos_totales;
    }

    public Seleccion(String continente, int conteo) {
        this.continente = continente;
        this.conteo = conteo;
    }

    public Seleccion(String nombre, String continente, String dt, String nacionalidad) {
        this.nombre = nombre;
        this.continente = continente;
        this.dt = dt;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}

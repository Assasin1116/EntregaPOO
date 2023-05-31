/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO j_monsalve3.seleccion (nombres, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT nombres, continente, dt, nacionalidad FROM j_monsalve3.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getSeleccionesBusqueda(String nombreSeleccion) {
        System.out.println("LLEGAMOS HASTA GETSELECCIONESBUSQUEDA");
        String sql = "SELECT nombres, continente, dt, nacionalidad FROM j_monsalve3.seleccion WHERE nombres LIKE ?";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            if (BasedeDatos.conexion == null) {
                // Mostrar un mensaje de error o lanzar una excepción
                System.out.println("No hay conexión a la base de datos");
                return selecciones;
            }
            // Preparar la consulta SQL y establecer el parámetro
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreSeleccion + "%");

            // Ejecutar la consulta y obtener el resultado
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public int cantidad_de_equipos() {
        String sql = "select count(*) AS cantidad from j_monsalve3.seleccion";
        int numero_equipos = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {

                while (result.next()) {
                    numero_equipos = result.getInt(1);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }
        return numero_equipos;
    }

    public List<Seleccion> equipos_continente() {
        String sql = "select continente, count(continente) as conteo from j_monsalve3.seleccion s group by continente having count(continente)>1";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {

                while (result.next()) {
                    String nombres = result.getString("continente");
                    int asia = result.getInt("conteo");
                    System.out.println(nombres + " : " + asia);
                    Seleccion seleccion = new Seleccion(result.getString("continente"), result.getInt("conteo"));

                    selecciones.add(seleccion);

                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones continentes");
        }
        return selecciones;

    }

    public List<Seleccion> cantidadnacionalidades() {
        String sql = "SELECT nacionalidad, COUNT(*) AS cantidad_directores_tecnicos\n"
                + "FROM j_monsalve3.seleccion s \n"
                + "GROUP BY nacionalidad;";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {

                while (result.next()) {
                    String nombres = result.getString("nacionalidad");
                    int conteo = result.getInt("cantidad_directores_tecnicos");
                    System.out.println(nombres + " : " + conteo);
                    Seleccion seleccion = new Seleccion(result.getString("nacionalidad"), result.getInt("cantidad_directores_tecnicos"));

                    selecciones.add(seleccion);

                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones continentes");
        }

        return selecciones;

    }

    public List<Seleccion> ranking() {
         String sql = "SELECT nacionalidad, cantidad_directores_tecnicos, " +
                    "       ROW_NUMBER() OVER (ORDER BY cantidad_directores_tecnicos DESC) AS ranking " +
                    "FROM ( " +
                    "    SELECT nacionalidad, COUNT(*) AS cantidad_directores_tecnicos " +
                    "    FROM j_monsalve3.seleccion " +
                    "    GROUP BY nacionalidad " +
                    ") AS subquery";
         
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {    ResultSet result = BasedeDatos.ejecutarSQL(sql);

            // Recorrer los resultados y crear objetos de la clase Seleccion
            while (result.next()) {
                String nacionalidad = result.getString("nacionalidad");
                int cantidadDirectoresTecnicos = result.getInt("cantidad_directores_tecnicos");
                int ranking = result.getInt("ranking");

                Seleccion seleccion = new Seleccion(nacionalidad, cantidadDirectoresTecnicos, ranking);
                selecciones.add(seleccion);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones continentes");
        }
        return selecciones;
    }
}

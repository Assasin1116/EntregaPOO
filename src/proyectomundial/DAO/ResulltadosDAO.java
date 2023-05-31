/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.*;
import proyectomundial.util.BasedeDatos;
import java.text.DecimalFormat;

/**
 *
 * @author LAB205PC16
 */
public class ResulltadosDAO {

    public ResulltadosDAO() {
        BasedeDatos.conectar();
    }

    public static boolean registrarResultados(Resultados resultados) {
        System.out.println("registrar resultados");
        String sql = "INSERT INTO j_monsalve3.partidos (grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante) values("
                + "'" + resultados.getGrupo() + "', "
                + "'" + resultados.getLocal() + "', "
                + "'" + resultados.getVisitante() + "', "
                + "'" + resultados.getContinente_local() + "',"
                + "'" + resultados.getContinente_visitante() + "',"
                + "'" + resultados.getGoles_locales() + "',"
                + "'" + resultados.getGoles_visitante() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Resultados> getResultados() {
        System.out.println("getresultados");
        String sql = "SELECT grupo,local,visitante,continente_local,continente_visitante,goles_local,goles_visitante FROM j_monsalve3.partidos";
        List<Resultados> resultados = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados 2 ");
        }

        return resultados;
    }

    public List<Resultados> getresultadosbusquedas(String nombreS) {
        System.out.println("resultados selecion busqueda");
        String sql = "SELECT grupo,local,visitante,continente_local,continente_visitante,goles_local,goles_visitante FROM j_monsalve3.partidos WHERE visitante LIKE ? or local LIKE ?";
        List<Resultados> resultados = new ArrayList<Resultados>();
        try {
            if (BasedeDatos.conexion == null) {
                System.out.println("no hay conexion");
                return resultados;
            }
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreS + "%");
            stmt.setString(2, "%" + nombreS + "%");
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("error en los resultados 3 ");
        }
        return resultados;
    }

    public int cantidapartidos() {
        int cantidadPartidos = 0;
        String sql = "SELECT COUNT(*) AS cantidad_partidos\n"
                + "FROM j_monsalve3.partidos p;";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                cantidadPartidos = result.getInt("cantidad_partidos");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando cantidad de partidos ");
        }

        return cantidadPartidos;
    }



public double promediogoles() {
    String sql = "SELECT AVG(goles_local) AS promedio_loca, AVG(goles_visitante) AS promedio_vis FROM j_monsalve3.partidos p";
    double promedio = 0;

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result.next()) {
            double promedioLocal = result.getDouble("promedio_loca");
            double promedioVisitante = result.getDouble("promedio_vis");
            promedio = promedioLocal + promedioVisitante;

            // Limita a 2 decimales
            BigDecimal bd = new BigDecimal(promedio);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            promedio = bd.doubleValue();
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando promedio goles");
    }

    return promedio;
}

public String masgoles() {
    String sql = "SELECT * FROM j_monsalve3.partidos ORDER BY goles_local + goles_visitante DESC LIMIT 1";
    String resultado = "";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result.next()) {
            String equipoLocal = result.getString("local");
            String equipoVisitante = result.getString("visitante");
            int golesLocal = result.getInt("goles_local");
            int golesVisitante = result.getInt("goles_visitante");

            resultado = "Partido con más goles:\n";
            resultado += " Equipo local: " + equipoLocal + "\n";
            resultado += " Equipo visitante: " + equipoVisitante + "\n";
            resultado += " Goles local: " + golesLocal + "\n";
            resultado += " Goles visitante: " + golesVisitante;
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando partido con más goles");
    }

    return resultado;
}

public String menosgoles() {
    String sql = "SELECT * FROM j_monsalve3.partidos ORDER BY goles_local + goles_visitante ASC LIMIT 1";
    String resultado = "";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result.next()) {
            String equipoLocal = result.getString("local");
            String equipoVisitante = result.getString("visitante");
            int golesLocal = result.getInt("goles_local");
            int golesVisitante = result.getInt("goles_visitante");

            resultado = "Partido con menos goles:\n";
            resultado += " Equipo local: " + equipoLocal + "\n";
            resultado += " Equipo visitante: " + equipoVisitante + "\n";
            resultado += " Goles local: " + golesLocal + "\n";
            resultado += " Goles visitante: " + golesVisitante;
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando partido con menos goles");
    }

    return resultado;
}
public String ganados() {
    String sql = "SELECT COUNT(*) AS total_ganados FROM j_monsalve3.partidos WHERE goles_local <> goles_visitante";
    String resultado = "";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result.next()) {
            int totalGanados = result.getInt("total_ganados");
            resultado = "Número de partidos con ganador: " + totalGanados;
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error obteniendo el número de partidos con ganador");
    }

    return resultado;
}
public String empate() {
    String sql = "SELECT COUNT(*) AS total_empates FROM j_monsalve3.partidos WHERE goles_local = goles_visitante";
    String resultado = "";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result.next()) {
            int totalEmpates = result.getInt("total_empates");
            resultado = "Número de partidos con empate: " + totalEmpates;
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error obteniendo el número de partidos con empate");
    }

    return resultado;
}
public String seleccionMas() {
    String sql = "SELECT equipo, puntos\n" +
        "FROM (\n" +
        "    SELECT equipo, puntos,\n" +
        "           ROW_NUMBER() OVER (ORDER BY puntos DESC) AS posicion_desc,\n" +
        "           ROW_NUMBER() OVER (ORDER BY puntos ASC) AS posicion_asc\n" +
        "    FROM (\n" +
        "        SELECT equipo, SUM(puntos) AS puntos\n" +
        "        FROM (\n" +
        "            SELECT local AS equipo, \n" +
        "                   CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n" +
        "            FROM j_monsalve3.partidos p2  \n" +
        "            GROUP BY local, goles_local, goles_visitante\n" +
        "            UNION ALL\n" +
        "            SELECT visitante AS equipo, \n" +
        "                   CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n" +
        "            FROM j_monsalve3.partidos p2  \n" +
        "            GROUP BY visitante, goles_local, goles_visitante\n" +
        "        ) AS subquery\n" +
        "        GROUP BY equipo\n" +
        "    ) AS subquery2\n" +
        ") AS subquery3\n" +
        "WHERE posicion_desc = 1 OR posicion_asc = 1\n" +
        "ORDER BY puntos DESC;";

    String resultado = "";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null && result.next()) {
            String equipo = result.getString("equipo");
            int puntos = result.getInt("puntos");

            resultado = "Equipo: " + equipo + ", Puntos: " + puntos;
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando el equipo con más puntos");
    }

    return resultado;
}


public String seleccionMenos() {
    String sql = "SELECT equipo, puntos\n" +
        "FROM (\n" +
        "    SELECT equipo, puntos,\n" +
        "           ROW_NUMBER() OVER (ORDER BY puntos DESC) AS posicion_desc,\n" +
        "           ROW_NUMBER() OVER (ORDER BY puntos ASC) AS posicion_asc\n" +
        "    FROM (\n" +
        "        SELECT equipo, SUM(puntos) AS puntos\n" +
        "        FROM (\n" +
        "            SELECT local AS equipo, \n" +
        "                   CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n" +
        "            FROM j_monsalve3.partidos p2  \n" +
        "            GROUP BY local, goles_local, goles_visitante\n" +
        "            UNION ALL\n" +
        "            SELECT visitante AS equipo, \n" +
        "                   CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n" +
        "            FROM j_monsalve3.partidos p2  \n" +
        "            GROUP BY visitante, goles_local, goles_visitante\n" +
        "        ) AS subquery\n" +
        "        GROUP BY equipo\n" +
        "    ) AS subquery2\n" +
        ") AS subquery3\n" +
        "WHERE posicion_desc = 1 OR posicion_asc = 1\n" +
        "ORDER BY puntos asc;";

    String resultado = "";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null && result.next()) {
            String equipo = result.getString("equipo");
            int puntos = result.getInt("puntos");

            resultado = "Equipo: " + equipo + ", Puntos: " + puntos;
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando el equipo con más puntos");
    }

    return resultado;
}
public String contientemasgoles() {
    String sql = "SELECT continente, SUM(total_goles) AS total_goles\n" +
                 "FROM (\n" +
                 "    SELECT continente_local AS continente, SUM(goles_local) AS total_goles\n" +
                 "    FROM j_monsalve3.partidos\n" +
                 "    GROUP BY continente_local\n" +
                 "    UNION ALL\n" +
                 "    SELECT continente_visitante AS continente, SUM(goles_visitante) AS total_goles\n" +
                 "    FROM j_monsalve3.partidos\n" +
                 "    GROUP BY continente_visitante\n" +
                 ") AS subquery\n" +
                 "GROUP BY continente\n" +
                 "ORDER BY total_goles DESC;";
                 
    String resultado = "";
    
    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);
        if (result != null) {
            while (result.next()) {
                String continente = result.getString("continente");
                int totalGoles = result.getInt("total_goles");
                resultado += continente + " : " + totalGoles + "\n";
            }
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando continentes con más goles");
    }
    
    return resultado;
}

public List<Resultados> obtenerGolesSeleccion() {
    String sql = "SELECT seleccion, total_goles\n" +
"FROM (\n" +
"    SELECT local AS seleccion, SUM(goles_local) AS total_goles\n" +
"    FROM j_monsalve3.partidos\n" +
"    GROUP BY local\n" +
"    UNION ALL\n" +
"    SELECT visitante AS seleccion, SUM(goles_visitante) AS total_goles\n" +
"    FROM j_monsalve3.partidos\n" +
"    GROUP BY visitante\n" +
") AS subquery\n" +
"ORDER BY total_goles desc limit 2;";

    List<Resultados> golesSelecciones = new ArrayList<>();

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null) {
            while (result.next()) {
                String seleccion = result.getString("seleccion");
                int totalGoles = result.getInt("total_goles");

                Resultados golesSeleccion = new Resultados(seleccion, totalGoles);
                golesSelecciones.add(golesSeleccion);
            }
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando goles por selección");
    }

    return golesSelecciones;
}

public List<Resultados> obtenerMenosGolesSeleccion() {
    String sql = "SELECT seleccion, total_goles\n" +
"FROM (\n" +
"    SELECT local AS seleccion, SUM(goles_local) AS total_goles\n" +
"    FROM j_monsalve3.partidos\n" +
"    GROUP BY local\n" +
"    UNION ALL\n" +
"    SELECT visitante AS seleccion, SUM(goles_visitante) AS total_goles\n" +
"    FROM j_monsalve3.partidos\n" +
"    GROUP BY visitante\n" +
") AS subquery\n" +
"ORDER BY total_goles asc limit 2;";

    List<Resultados> MenosgolesSelecciones = new ArrayList<>();

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null) {
            while (result.next()) {
                String seleccion = result.getString("seleccion");
                int totalGoles = result.getInt("total_goles");

                Resultados golesSeleccion = new Resultados(seleccion, totalGoles);
                MenosgolesSelecciones.add(golesSeleccion);
            }
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando goles por selección");
    }

    return MenosgolesSelecciones;
}
public List<Resultados> cuantospasan() {
    List<Resultados> equipos = new ArrayList<>();

    String sql = "SELECT grupo, equipo, puntos\n" +
        "FROM (\n" +
        "    SELECT grupo, equipo, puntos,\n" +
        "           ROW_NUMBER() OVER (PARTITION BY grupo ORDER BY puntos DESC) AS posicion_desc\n" +
        "    FROM (\n" +
        "        SELECT grupo, equipo, SUM(puntos) AS puntos\n" +
        "        FROM (\n" +
        "            SELECT grupo, local AS equipo, \n" +
        "                   CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n" +
        "            FROM j_monsalve3.partidos\n" +
        "            GROUP BY grupo, local, goles_local, goles_visitante\n" +
        "            UNION ALL\n" +
        "            SELECT grupo, visitante AS equipo, \n" +
        "                   CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n" +
        "            FROM j_monsalve3.partidos\n" +
        "            GROUP BY grupo, visitante, goles_local, goles_visitante\n" +
        "        ) AS subquery\n" +
        "        GROUP BY grupo, equipo\n" +
        "    ) AS subquery2\n" +
        ") AS subquery3\n" +
        "WHERE posicion_desc <= 2\n" +
        "ORDER BY grupo, puntos DESC;";

    try {
        ResultSet resultSet = BasedeDatos.ejecutarSQL(sql);

        if (resultSet != null) {
            while (resultSet.next()) {
                String grupo = resultSet.getString("grupo");
                String equipo = resultSet.getString("equipo");
                int puntos = resultSet.getInt("puntos");

                Resultados equipoObjeto = new Resultados(grupo, equipo, puntos);
                equipos.add(equipoObjeto);
            }
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando los equipos que pasan de cada grupo");
    }

    return equipos;
}



    public String[][] getResultadosMatriz() {

        String[][] matrizResultados = null;
        List<Resultados> resultados = getResultados();

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_locales();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }
}

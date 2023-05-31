package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import proyectomundial.DAO.ResulltadosDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Seleccion;
import proyectomundial.model.Resultados;
import proyectomundial.util.BasedeDatos;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResulltadosDAO resultadoDAO = new ResulltadosDAO();

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los Resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los Resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de Resultados
        pintarMenuDashboardRes();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();
        jPanelMain.setPreferredSize(new Dimension(1000, 700));
        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    public class TablaCantidadNacionalidades extends JFrame {

        private JTable table;
        private DefaultTableModel model;

        public TablaCantidadNacionalidades(List<Seleccion> selecciones) {
            setTitle("Cantidad de Nacionalidades");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el modelo de la tabla
            model = new DefaultTableModel();
            model.addColumn("Nacionalidad");
            model.addColumn("Cantidad");

            // Agregar los datos a la tabla
            for (Seleccion seleccion : selecciones) {
                model.addRow(new Object[]{seleccion.getNacionalidad(), seleccion.getCantidad()});
            }

            // Crear la tabla y establecer el modelo
            table = new JTable(model);

            // Agregar la tabla a un panel con desplazamiento
            JScrollPane scrollPane = new JScrollPane(table);

            // Agregar el panel al marco
            add(scrollPane);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    private class Tablamasgolesseleciones extends JFrame {

        private JTable table;
        private DefaultTableModel model;

        public Tablamasgolesseleciones(List<Resultados> Resultados) {
            setTitle("Cantidad de Nacionalidades");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el modelo de la tabla
            model = new DefaultTableModel();
            model.addColumn("selecion");
            model.addColumn("total goles");

            // Agregar los datos a la tabla
            for (Resultados resultados : Resultados) {
                model.addRow(new Object[]{resultados.getSeleccion(), resultados.getTotalGoles()});
            }

            // Crear la tabla y establecer el modelo
            table = new JTable(model);

            // Agregar la tabla a un panel con desplazamiento
            JScrollPane scrollPane = new JScrollPane(table);

            // Agregar el panel al marco
            add(scrollPane);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadoDAO.getResultadosMatriz();
        // Si no hay Resultados cargados, muestra el botón de carga de Resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de Resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardSel() {
        jLabelTop.setText("Dash seleciones");
        JPanel a = new JPanel();
        a.setPreferredSize(new Dimension(150, 100));
        a.setBackground(Color.LIGHT_GRAY);
        // cantidad de seleciones
        JLabel label = new JLabel();
        label.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 45;'>" + seleccionDAO.cantidad_de_equipos() + "</span><br>Seleciones</div></html>");
        label.setBackground(Color.LIGHT_GRAY);
        a.add(label);
        // cantidad de equipos de continentes
        JTextArea cantidad = new JTextArea();
        cantidad.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        cantidad.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        JLabel label2 = new JLabel();
        label2.setForeground(Color.black);
        Dimension dimensioncan = new Dimension(200, 120);
        cantidad.setBounds(100, 100, 100, 100);
        cantidad.setPreferredSize(dimensioncan);
        cantidad.setBackground(Color.LIGHT_GRAY);
        List<Seleccion> seleccionesBusqueda = seleccionDAO.equipos_continente();
        String texto = "Equipos Por Continente\n\n";
        for (Seleccion seleccion : seleccionesBusqueda) {
            texto += seleccion.getContinente() + ": " + seleccion.getConteo() + "\n";
        }
        label2.add(cantidad);
        cantidad.setText(texto);
        a.add(label2);
        a.add(cantidad);
// tecnicos por nacionalidades
        JPanel a2 = new JPanel();
        a2.setPreferredSize(new Dimension(300, 410));
        a2.setBackground(Color.LIGHT_GRAY);
        JTextArea textodt = new JTextArea();
        textodt.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        textodt.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncdt = new Dimension(200, 600);
        textodt.setBounds(100, 100, 100, 100);
        textodt.setPreferredSize(dimensioncdt);
        textodt.setBackground(Color.LIGHT_GRAY);
        JLabel labeldt = new JLabel();
        labeldt.setForeground(Color.black);
        labeldt.setBackground(Color.LIGHT_GRAY);
        List<Seleccion> selecciones = seleccionDAO.cantidadnacionalidades();
        String dt = "Cantidad de nacionalidades \n en directores tecnicos \n";
        for (Seleccion seleccion : selecciones) {
            dt += seleccion.getContinente() + ": " + seleccion.getConteo() + "\n";
        }
        labeldt.add(textodt);
        textodt.setText(dt);
        a2.add(labeldt);
        a2.add(textodt);

        // ranking 
        JPanel a3 = new JPanel();
        a3.setPreferredSize(new Dimension(300, 400));
        a3.setBackground(Color.LIGHT_GRAY);
        JTextArea ranking = new JTextArea();
        ranking.setBackground(Color.LIGHT_GRAY);
        ranking.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        ranking.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncRK = new Dimension(300, 600);
        textodt.setBounds(100, 100, 100, 100);
        textodt.setPreferredSize(dimensioncRK);
        textodt.setBackground(Color.LIGHT_GRAY);
        textodt.setForeground(Color.white);
        JLabel labelRK = new JLabel();
        labelRK.setBackground(Color.LIGHT_GRAY);
        labelRK.setForeground(Color.white);
        List<Seleccion> rankin = seleccionDAO.ranking();
        String ran = "RANKING \n";
        for (Seleccion seleccion : rankin) {
            ran += seleccion.getNacionalidad() + " cantidad: " + seleccion.getCantidad_directores_tecnicos() + " ranking: " + seleccion.getRanking() + "\n";
        }
        System.out.println(ran);
        labelRK.add(ranking);
        ranking.setText(ran);
        a3.add(labelRK);
        a3.add(ranking);

        jPanelMain.removeAll();
        jPanelMain.add(cantidad);
        jPanelMain.add(a);
        jPanelMain.add(a2);
        jPanelMain.add(a3);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dash Resultados");

        // cantidad de partidos
        JPanel r1 = new JPanel();
        r1.setPreferredSize(new Dimension(100, 100));
        r1.setBackground(Color.LIGHT_GRAY);
        JTextArea PARTIDOS = new JTextArea();
        PARTIDOS.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        PARTIDOS.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncdt = new Dimension(200, 600);
        PARTIDOS.setBounds(100, 100, 100, 100);
        PARTIDOS.setPreferredSize(dimensioncdt);
        PARTIDOS.setBackground(Color.LIGHT_GRAY);
        JLabel labelPT = new JLabel();
        labelPT.setForeground(Color.black);
        labelPT.setBackground(Color.LIGHT_GRAY);
        labelPT.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPT.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 45;'>" + resultadoDAO.cantidapartidos() + "</span><br>Partidos</div></html>");
        r1.add(labelPT);

        // promedio de goles por partidos
        JPanel r2 = new JPanel();
        r2.setPreferredSize(new Dimension(100, 100));
        r2.setBackground(Color.LIGHT_GRAY);
        JTextArea promedior = new JTextArea();
        promedior.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        promedior.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr = new Dimension(200, 600);
        promedior.setBounds(100, 100, 100, 100);
        promedior.setPreferredSize(dimensioncr);
        promedior.setBackground(Color.LIGHT_GRAY);
        JLabel labelPromedio = new JLabel();
        labelPromedio.setForeground(Color.black);
        labelPromedio.setBackground(Color.LIGHT_GRAY);
        labelPromedio.setText("<html><div style='text-align: center; font-size: 12pt;'><span style='font-size: 45;'>" + resultadoDAO.promediogoles() + "</span><br>promedio goles</div></html>");
        r2.add(labelPromedio);

        // Partido con más y menos goles 
        JPanel r3 = new JPanel();
        r3.setPreferredSize(new Dimension(700, 50));
        r3.setBackground(Color.LIGHT_GRAY);
        JTextArea mas = new JTextArea();
        mas.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        mas.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr3 = new Dimension(700, 50);
        mas.setBounds(100, 100, 100, 100);
        mas.setPreferredSize(dimensioncr3);
        mas.setBackground(Color.LIGHT_GRAY);
        JLabel labelmas = new JLabel();
        labelmas.setForeground(Color.black);
        labelmas.setBackground(Color.LIGHT_GRAY);
        labelmas.setText("<html><div style='text-align: center; font-size: 12pt;'><span style='font-size: 12pt;'>" + resultadoDAO.masgoles() + "</span><br>" + resultadoDAO.menosgoles() + "</div></html>");
        r3.add(labelmas);

        // Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate
        JPanel r4 = new JPanel();
        r4.setPreferredSize(new Dimension(250, 50));
        r4.setBackground(Color.LIGHT_GRAY);
        JTextArea ganados = new JTextArea();
        ganados.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        ganados.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr4 = new Dimension(250, 50);
        ganados.setBounds(100, 100, 100, 100);
        ganados.setPreferredSize(dimensioncr4);
        ganados.setBackground(Color.LIGHT_GRAY);
        JLabel labelganados = new JLabel();
        labelganados.setForeground(Color.black);
        labelganados.setBackground(Color.LIGHT_GRAY);
        labelganados.setText("<html><div style='text-align: center; font-size: 12pt;'><span style='font-size: 12pt;'>" + resultadoDAO.ganados() + "</span><br>" + resultadoDAO.empate() + "</div></html>");
        r4.add(labelganados);

        // Selección con más puntos y menos puntos
        JPanel r5 = new JPanel();
        r5.setPreferredSize(new Dimension(250, 50));
        r5.setBackground(Color.LIGHT_GRAY);
        JTextArea goles = new JTextArea();
        goles.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        goles.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr5 = new Dimension(250, 50);
        goles.setBounds(100, 100, 100, 100);
        goles.setPreferredSize(dimensioncr5);
        goles.setBackground(Color.LIGHT_GRAY);
        JLabel labelgoles = new JLabel();
        labelgoles.setForeground(Color.black);
        labelgoles.setBackground(Color.LIGHT_GRAY);
        labelgoles.setText("<html><div style='text-align: center; font-size: 12pt;'><span style='font-size: 12pt;'>" + "Mas Puntos " + resultadoDAO.seleccionMas() + "</span><br>" + " Menos Puntos " + resultadoDAO.seleccionMenos() + "</div></html>");
        r5.add(labelgoles);

        // Continente o continentes con más goles y menos goles
        JPanel r6 = new JPanel();
        r6.setPreferredSize(new Dimension(600, 50));
        r6.setBackground(Color.LIGHT_GRAY);
        JTextArea contiente = new JTextArea();
        contiente.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        contiente.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr6 = new Dimension(600, 50);
        contiente.setBounds(100, 100, 100, 100);
        contiente.setPreferredSize(dimensioncr6);
        contiente.setBackground(Color.LIGHT_GRAY);
        JLabel labelcontinetne = new JLabel();
        labelcontinetne.setForeground(Color.black);
        labelcontinetne.setBackground(Color.LIGHT_GRAY);
        labelcontinetne.setText("<html><div style='text-align: center; font-size: 17pt;'>Continente mas goles a menos goles<br><span style='font-size: 12;'>" + resultadoDAO.contientemasgoles() + "</span><br></div></html>");
        r6.add(labelcontinetne);

        // 5. Selcción o selecciones con más goles y con menos goles
        JPanel r7 = new JPanel();
        r7.setPreferredSize(new Dimension(300, 50));
        r7.setBackground(Color.LIGHT_GRAY);
        JTextArea goless = new JTextArea();
        goless.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        goless.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr7 = new Dimension(300, 50);
        goless.setBounds(100, 100, 100, 100);
        goless.setPreferredSize(dimensioncr7);
        goless.setBackground(Color.LIGHT_GRAY);
        JLabel labelgoless = new JLabel();
        labelgoless.setForeground(Color.black);
        labelgoless.setBackground(Color.blue);
        labelgoless.setText("mas goles de selecion");
        List<Resultados> golesSelecciones = resultadoDAO.obtenerGolesSeleccion();
        JTable tabla = new JTable();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Equipo");
        modeloTabla.addColumn("Goles");

        // Agregar los datos al modelo de la tabla
        for (Resultados resultado : golesSelecciones) {
            Object[] fila = {resultado.getSeleccion(), resultado.getTotalGoles()};
            modeloTabla.addRow(fila);
        }

        // Establecer el modelo de la tabla
        tabla.setModel(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabla de Goles de Selecciones"));
        // Configurar la apariencia del título del JScrollPane
        TitledBorder titledBorder = (TitledBorder) scrollPane.getBorder();
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        labelgoless.add(tabla);
        r7.add(tabla);
        r7.add(labelgoless);

        // mejos goles selecion
        JPanel r8 = new JPanel();
        r8.setPreferredSize(new Dimension(300, 70));
        r8.setBackground(Color.LIGHT_GRAY);
        JTextArea Menosgoles = new JTextArea();
        Menosgoles.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        Menosgoles.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        Dimension dimensioncr8 = new Dimension(300, 70);
        Menosgoles.setBounds(100, 100, 100, 100);
        Menosgoles.setPreferredSize(dimensioncr8);
        Menosgoles.setBackground(Color.LIGHT_GRAY);
        JLabel labelmenosgoles = new JLabel();
        labelmenosgoles.setForeground(Color.black);
        labelmenosgoles.setBackground(Color.blue);
        labelmenosgoles.setText("menos goles de selecion");
        List<Resultados> golesMneosSelecciones = resultadoDAO.obtenerMenosGolesSeleccion();
        JTable tabla2 = new JTable();
        DefaultTableModel modeloTabla2 = new DefaultTableModel();
        modeloTabla2.addColumn("Equipo");
        modeloTabla2.addColumn("Goles");

        // Agregar los datos al modelo de la tabla
        for (Resultados resultado : golesMneosSelecciones) {
            Object[] fila2 = {resultado.getSeleccion(), resultado.getTotalGoles()};
            modeloTabla2.addRow(fila2);
        }

        // Establecer el modelo de la tabla
        tabla2.setModel(modeloTabla2);
        JScrollPane scrollPane2 = new JScrollPane(tabla2);
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Tabla de Goles de Selecciones"));
        // Configurar la apariencia del título del JScrollPane
        TitledBorder titledBorder2 = (TitledBorder) scrollPane.getBorder();
        titledBorder2.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder2.setTitleJustification(TitledBorder.CENTER);
        titledBorder2.setTitlePosition(TitledBorder.TOP);
        Menosgoles.add(tabla2);
        r8.add(labelmenosgoles);
        r8.add(tabla2);
        r8.add(Menosgoles);

        //8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo
        JPanel r9 = new JPanel();
        r9.setPreferredSize(new Dimension(300, 570));
        r9.setBackground(Color.LIGHT_GRAY);

        JLabel labelpasan = new JLabel();
        labelpasan.setForeground(Color.black);
        labelpasan.setBackground(Color.blue);
        labelpasan.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelpasan.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        labelpasan.setPreferredSize(new Dimension(300, 30));

        List<Resultados> pasanSelecciones = resultadoDAO.cuantospasan();
        DefaultTableModel modeloTabla3 = new DefaultTableModel();
        modeloTabla3.addColumn("Grupo");
        modeloTabla3.addColumn("Equipo");
        modeloTabla3.addColumn("Puntos");

// Agregar los datos al modelo de la tabla
        for (Resultados equipo : pasanSelecciones) {
            Object[] fila = {equipo.getGrupo(), equipo.getEquipo(), equipo.getPuntos()};
            modeloTabla3.addRow(fila);
        }

        JTable tabla3 = new JTable(modeloTabla3);
        tabla3.setPreferredSize(new Dimension(300, 400));

        JScrollPane scrollPane3 = new JScrollPane(tabla3);
        scrollPane3.setBorder(BorderFactory.createTitledBorder("Equipos que pasan por grupo"));
        TitledBorder titledBorder3 = (TitledBorder) scrollPane3.getBorder();
        titledBorder3.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder3.setTitleJustification(TitledBorder.CENTER);
        titledBorder3.setTitlePosition(TitledBorder.TOP);

        JPanel panelPasan = new JPanel();
        panelPasan.setLayout(new BorderLayout());
        panelPasan.setPreferredSize(new Dimension(300, 500));
        panelPasan.add(labelpasan, BorderLayout.NORTH);
        panelPasan.add(scrollPane3, BorderLayout.CENTER);

        r9.add(panelPasan);

        /*
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"
                + "2. Promedio de goles por partido \n"
                + "3. Partido con más goles y partido con menos goles \n"
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
                + "5. Selcción o selecciones con más goles y con menos goles \n"
                + "6. Selección con más puntos y menos puntos \n"
                + "7. Continente o continentes con más goles y menos goles \n"
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
         */
        jPanelMain.removeAll();
        jPanelMain.add(r1);
        jPanelMain.add(r2);
        jPanelMain.add(r3);
        jPanelMain.add(r4);
        jPanelMain.add(r5);
        jPanelMain.add(r6);
        jPanelMain.add(r7);
        jPanelMain.add(r8);
        jPanelMain.add(r9);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field.setText("");
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

                table.setModel(modeloTabla);
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Resultados resultados = new Resultados(columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7]);
                if (resultadoDAO.registrarResultados(resultados)) {
                    System.out.println("Resultado " + resultados.getGrupo() + " registrada");
                } else {
                    System.out.println("Error " + resultados.getGrupo());
                }

            }
            resultados = resultadoDAO.getResultadosMatriz();
            pintarTablaResultados();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * Resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                //   List<Resultados> ResultadosBusqueda = ResulltadosDAO.
                List<Resultados> resultadosBusqueda = resultadoDAO.getresultadosbusquedas(field.getText());

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinente_local(), resultado.getContinente_visitante(), resultado.getGoles_locales(), resultado.getGoles_visitante()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                field.setText("");
                List<Resultados> resultadosBusqueda = resultadoDAO.getresultadosbusquedas("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinente_local(), resultado.getContinente_visitante(), resultado.getGoles_locales(), resultado.getGoles_visitante()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(1000, 950)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(1000, 950)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(1000, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}

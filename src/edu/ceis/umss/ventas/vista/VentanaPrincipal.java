/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista;

import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.modelo.ListaDeVentas;

import java.awt.Font;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author
 */
public class VentanaPrincipal extends JFrame {

    private JTabbedPane tabs;
    private ListaDeVentas lista;
    private ContenedorDeProductos contenedorDeProductos;

    public VentanaPrincipal(ContenedorDeProductos contenedorDeProductos) throws ParseException {
        super("Control de Ventas");
        this.iniciarComponentes();
        lista = new ListaDeVentas(new Date(), contenedorDeProductos);
        this.contenedorDeProductos = contenedorDeProductos;
        this.aderirComponentes();
        setSize(1400, 725);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void iniciarComponentes() {
        tabs = new JTabbedPane();
    }

    private void aderirComponentes() {

        // tabs.setLayout(new BorderLayout());
        PanelAlmacen panelAlmacen = new PanelAlmacen(contenedorDeProductos);

        tabs.add("Registro", new PanelRegistroDeProducto(contenedorDeProductos, panelAlmacen));
        tabs.add("Busqueda y seleccion", new PanelBusquedaYseleccion(lista, contenedorDeProductos));
        tabs.add("Almacen", panelAlmacen);
        tabs.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(tabs);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista;

import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.vista.modelo.ModeloTablaDelAlmacen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author
 */
public class PanelAlmacen extends JPanel {

    private ContenedorDeProductos contenedorDeProductos;
    private ModeloTablaDelAlmacen modelo;
    private JTable tablero;

    public PanelAlmacen(ContenedorDeProductos contenedorDeProductos) {
        setLayout(new BorderLayout());
        this.contenedorDeProductos = contenedorDeProductos;
        iniciarComponentes();
        aderirComponentes();
        setVisible(true);

    }

    private void iniciarComponentes() {
        modelo = new ModeloTablaDelAlmacen(contenedorDeProductos);
        tablero = new JTable(modelo);
        tablero.setRowHeight(40);
        Dimension dim = new Dimension(20, 20);
        tablero.setIntercellSpacing(new Dimension(dim));
        tablero.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tablero.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
    }

    public ModeloTablaDelAlmacen getModeloTablaAlmacen() {
        tablero.updateUI();
        return modelo;
    }

    private void aderirComponentes() {
        add(new JScrollPane(tablero));
    }
}

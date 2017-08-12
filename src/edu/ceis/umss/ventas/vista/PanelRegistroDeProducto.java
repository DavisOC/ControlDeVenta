/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista;

import edu.ceis.umss.ventas.controlador.EditarProductoAccion;
import edu.ceis.umss.ventas.controlador.EliminarProductoAccion;
import edu.ceis.umss.ventas.controlador.RegistrarProductoAccion;
import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.vista.modelo.ModeloDeRegistro;

import javax.swing.*;
import java.awt.*;

/**
 * @author
 */
public class PanelRegistroDeProducto extends JPanel {

    private final ContenedorDeProductos contenedor;
    private ModeloDeRegistro modeloDeRegistro;
    private JTable tablero;
    private PanelAlmacen panel;

    public PanelRegistroDeProducto(ContenedorDeProductos contenedor, PanelAlmacen panel) {
        this.panel = panel;
        this.contenedor = contenedor;
        inicializando();
        inicializandoBotonesDelRegistro();
    }

    private void inicializando() {
        setLayout(new BorderLayout());
        modeloDeRegistro = new ModeloDeRegistro(contenedor, panel);
        tablero = new JTable(modeloDeRegistro);
        tablero.setRowHeight(40);
        Dimension dim = new Dimension(20, 20);
        tablero.setIntercellSpacing(new Dimension(dim));
        tablero.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tablero.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        add(new JScrollPane(tablero), BorderLayout.CENTER);
        tablero.updateUI();
    }

    private void inicializandoBotonesDelRegistro() {
        JToolBar toolbar = new JToolBar("Registrar");
        JButton button1 = new JButton(new RegistrarProductoAccion(modeloDeRegistro, tablero));
        toolbar.add(button1);
        JButton button2 = new JButton(new EditarProductoAccion(tablero, modeloDeRegistro));
        toolbar.add(button2);
        JButton button3 = new JButton(new EliminarProductoAccion(modeloDeRegistro, tablero));
        toolbar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        toolbar.add(button3);
        add(toolbar, BorderLayout.PAGE_START);
    }

}

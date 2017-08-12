/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista.modelo;

import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.modelo.Producto;
import edu.ceis.umss.ventas.vista.PanelAlmacen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 *
 * @author
 */
public class ModeloDeRegistro extends AbstractTableModel {

    private static final int NUMERO_DE_COLUMNAS = 4;
    private static final String[] cabezeras = {"Codigo", "Nombre del Producto", "Precio", "Fecha de Vencimiento"};
    private final Map<Long, Producto> contenedor;
    private final List<Producto> lista;
    private PanelAlmacen panel;
    public ModeloDeRegistro(ContenedorDeProductos contenedorDeProducto, PanelAlmacen panel) {
        this.panel = panel;
        contenedor = contenedorDeProducto.getContenedorDeProducto();
        lista = new ArrayList<>(contenedor.values());
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public String getColumnName(int column) {
        return cabezeras[column];
    }

    @Override
    public int getColumnCount() {
        return NUMERO_DE_COLUMNAS;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            default:
                return Date.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return producto.getCodigo();
            case 1:
                return producto.getNombre();
            case 2:
                return producto.getPrecioUnitario();
            case 3:
                return producto.getFechaDeVencimiento();
            default:
                return null;
        }
    }

    public int getNumberOfRows() {
        return getRowCount();
    }

    public int getNumberOfColumns() {
        return getColumnCount();
    }

    public String getColumnHeader(int column) {
        return getColumnName(column);
    }

    public void remove(int selectedIndex) {
        Producto eliminado = lista.remove(selectedIndex);
        contenedor.remove(eliminado.getCodigo());
        fireTableRowsDeleted(selectedIndex, selectedIndex);
    }

    public Producto getProductoDe(int index) {
        return lista.get(index);
    }

    public void editar(Producto producto) {
        String nombre = producto.getNombre();
        final long codigo = producto.getCodigo();
        if (contenedor.containsKey(codigo) && !esElMismoNombre(nombre)) {
            lista.add(producto);
            contenedor.put(producto.getCodigo(), producto);
        }
    }

    public void agregar(Producto producto) {
        if (producto == null) {
            return;
        }
        String nombre = producto.getNombre();
        final long codigo = producto.getCodigo();
        if (!contenedor.containsKey(codigo) && !esElMismoNombre(nombre)) {
            lista.add(producto);
            contenedor.put(producto.getCodigo(), producto);
            panel.getModeloTablaAlmacen().editar(producto);
        } else {
            if (contenedor.containsKey(codigo)) {

                JOptionPane.showMessageDialog(new JFrame(),
                        "No se puede añadir codigo duplicados: " + String.valueOf(codigo),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (esElMismoNombre(nombre)) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "No se puede añadir Nombres duplicados: " + nombre,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }


        }
    }

    private boolean esElMismoNombre(String nombreBuscado) {
        for (Producto producto : lista) {
            if (producto.getNombre().equals(nombreBuscado)) {
                return true;
            }
        }
        return false;
    }
}

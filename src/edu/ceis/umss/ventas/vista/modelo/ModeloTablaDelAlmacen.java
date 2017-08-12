/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista.modelo;

import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.modelo.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public class ModeloTablaDelAlmacen extends AbstractTableModel {

    private static final int NUMERO_DE_COLUMNAS = 5;
    private static final String[] cabezeras = {"Codigo", "Nombre del Producto", "Precio", "Fecha de Vencimiento", "Cantidad"};
    private final Map<Long, Producto> contenedor;
    private final List<Producto> lista;
    private int[][] datosDeFila;

    public ModeloTablaDelAlmacen(ContenedorDeProductos contenedorDeProducto) {
        contenedor = contenedorDeProducto.getContenedorDeProducto();
        lista = new ArrayList<>(contenedor.values());
        datosDeFila = new int[lista.size()][lista.size()];
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
            case 4:
                return Integer.class;
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
            case 4:
                return producto.getCantidad();
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return col == 4;
    }

    public void setValueAt(Object value, int row, int col) {
        if (col == 4) {
            datosDeFila[row][col] = (int) value; // save edits some where
            Producto producto = lista.get(row);
            producto.setCantidad(datosDeFila[row][col]);

            fireTableCellUpdated(row, col); // informe any object about changes    
        } else {
            return;
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

    public void editar(Producto producto) {
        final long codigo = producto.getCodigo();
        if (contenedor.containsKey(codigo)) {
            lista.add(producto);
            contenedor.put(producto.getCodigo(), producto);
        }
    }

}

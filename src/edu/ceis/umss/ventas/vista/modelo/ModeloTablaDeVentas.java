/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista.modelo;


import edu.ceis.umss.ventas.modelo.ListaDeVentas;
import edu.ceis.umss.ventas.modelo.Producto;
import edu.ceis.umss.ventas.modelo.Transaccion;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

/**
 * @author Softure04
 */
public class ModeloTablaDeVentas extends AbstractTableModel {

    private static final int NUMERO_DE_COLUMNAS = 4;
    private static final String[] COLUMNA_NOMBRES = {"Codigo",
            "Nombre del Producto", "Precio", "Fecha de Vencimiento"};
    private ListaDeVentas listaDeVentas;
    private int contador;
    private Transaccion transaccion;

    public ModeloTablaDeVentas(ListaDeVentas listaDeVentas) {
        this.listaDeVentas = listaDeVentas;
        contador = 1;
        construirTransaccion();
        this.transaccion = listaDeVentas.getListaDeVentas().get(listaDeVentas.getListaDeVentas().size() - 1);
    }

    @Override
    public int getRowCount() {
        return transaccion.getProductosVendidos().size();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNA_NOMBRES[column];
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
            case 3:
                return Date.class;
            case 4:
                return Integer.class;
            default:
                return Double.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Producto producto = transaccion.getProductosVendidos().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return producto.getCodigo();
            case 1:
                return producto.getNombre();
            case 2:
                return producto.getPrecioUnitario();
            case 3:
                return producto.getPrecioUnitario();
            case 4:
                return 1;
            default:
                return null;
        }
    }

    //    @Override
//    public void propertyChange(PropertyChangeEvent pce) {
//        fireTableDataChanged();
//    }
    public int getNumberOfRows() {
        return getRowCount();
    }

    public int getNumberOfColumns() {
        return getColumnCount();
    }

    public String getColumnHeader(int column) {
        return getColumnName(column);
    }

    public void construirTransaccion() {
        listaDeVentas.aniadirTransaccion(new Transaccion("123" + contador, new Date()));
        contador++;
    }

    public void setLista(List<Producto> lista) {
        transaccion.setProductosVendidos(lista);
    }

    public double getPrecio() {
        return transaccion.getTotalVenta();
    }

    public void setPrecio(Double totalVenta) {
        transaccion.setTotalVenta(totalVenta);
    }

    public List<Producto> getListaDeVentas() {
        return transaccion.getProductosVendidos();
    }

    public void aniadirVenta(Producto producto) {
        transaccion.aniadirProducto(producto);
    }
}

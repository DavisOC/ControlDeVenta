/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public class ListaDeVentas {

    private List<Transaccion> listaDeVentas;
    private Date fechaDeVenta;
    private Map<Long, Producto> contenedor;

    public ListaDeVentas(Date fechaDeVenta, ContenedorDeProductos cont) throws ParseException {
        contenedor = cont.getContenedorDeProducto();
        listaDeVentas = new ArrayList<>();
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaDeVenta = fechaDeVenta;
    }

    public void aniadirTransaccion(Transaccion transaccion) {
        listaDeVentas.add(transaccion);

    }

    public void eliminarTransaccion(Transaccion transaccion) {
        listaDeVentas.remove(transaccion);
    }

    public List<Producto> ordenarListaDeProductos() {
        int cont = 0;
        Producto actual = null;
        List<Producto> listaOrdenada = new ArrayList<>();
        for (Map.Entry<Long, Producto> entry : contenedor.entrySet()) {
            Producto producto1 = entry.getValue();
            Long codigo = entry.getKey();
            for (Transaccion transaccion : listaDeVentas) {
                for (Producto producto : transaccion.getProductosVendidos()) {
                    if (codigo == producto.getCodigo()) {
                        cont = cont + 1;
                        actual = producto;
                    }
                }
            }
            if (actual != null) {
                actual.setCantidad(cont);
                listaOrdenada.add(actual);
            }
            actual = null;
            cont = 0;
        }
        return listaOrdenada;
    }

    public List<Transaccion> getListaDeVentas() {
        return listaDeVentas;
    }

    public void setListaDeVentas(List<Transaccion> listaDeVentas) {
        this.listaDeVentas = listaDeVentas;
    }

    public void mostrarLista() {
        for (Producto producto : ordenarListaDeProductos()) {
            System.out.println("Name " + producto.getNombre());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author
 */
public class ContenedorDeProductos {

    private Map<Long, Producto> contenedorDeProducto;

    public ContenedorDeProductos() {
        contenedorDeProducto = new HashMap<>();

    }

    public void aniadirProducto(Producto producto) {
        contenedorDeProducto.put(producto.getCodigo(), producto);
//        System.out.println("Producto : " + producto.getNombre());
//        System.out.println("Codigo : " + producto.getCodigo());
//        System.out.println("Fecha de Vencimiento : " + producto.getFechaDeVencimiento());
//        System.out.println("PrecioUnitario : " + producto.getPrecioUnitario());
    }

    public Map<Long, Producto> getContenedorDeProducto() {
        return contenedorDeProducto;
    }

    public void setContenedorDeProducto(Map<Long, Producto> contenedorDeProducto) {
        this.contenedorDeProducto = contenedorDeProducto;
    }

    public Producto buscarProducto(long codigo) {
        Producto producto = null;
        producto = contenedorDeProducto.get(codigo);
//        if (existeProducto(codigo)) {
//            producto = contenedorDeProducto.get(codigo);
//
//        } else {
//            System.out.println("No existe producto en el contendor");
//        }
        return producto;

    }

    private boolean existeProducto(long codigo) {

        boolean existe = contenedorDeProducto.containsKey(codigo);
        return existe;
    }

    public void eliminarProducto(long codigo) {
        Producto producto = contenedorDeProducto.remove(codigo);

        System.out.println("Producto eliminado: " + producto.getNombre());
    }

}

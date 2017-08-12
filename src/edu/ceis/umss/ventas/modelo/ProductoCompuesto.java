/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.text.ParseException;
/**
 *
 * @author
 */
public class ProductoCompuesto extends Producto {

    private int cantidad;
    private double precio;

    public ProductoCompuesto(String nombre, int codigo, int precioUnitario, String fechaDeVencimiento, int cantidad) throws ParseException {
        super(nombre, codigo, precioUnitario, fechaDeVencimiento);
        this.cantidad = cantidad;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double calculaPrecioCompuesto() {
        precio = cantidad * getPrecioUnitario();
        return precio;
    }
}

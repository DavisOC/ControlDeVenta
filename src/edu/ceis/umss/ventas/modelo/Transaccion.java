/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
public class Transaccion {

    private List<Producto> productosVendidos;
    private String codigoDeTransaccion;
    private Date fechaDeLaTransaccion;
    private double totalVenta;

    public Transaccion(String codigoDeTransaccion, Date fechaDeLaTransaccion) {
        totalVenta = 0.0D;
        productosVendidos = new ArrayList<>();
        this.codigoDeTransaccion = codigoDeTransaccion;
        this.fechaDeLaTransaccion = fechaDeLaTransaccion;
    }

    public void aniadirProducto(Producto producto) {
        productosVendidos.add(producto);
        totalVenta = totalVenta + producto.getPrecioUnitario();
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getCodigoDeTransaccion() {
        return codigoDeTransaccion;
    }

    public Date getFechaDeLaTransaccion() {
        return fechaDeLaTransaccion;
    }

    public List<Producto> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<Producto> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }


}

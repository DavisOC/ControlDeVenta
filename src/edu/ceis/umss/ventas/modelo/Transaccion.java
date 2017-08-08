/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
//        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            this.fechaDeLaTransaccion = formater.parse(fechaDeLaTransaccion);
//        } catch (ParseException ex) {
//            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void setProductosVendidos(List<Producto> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
    public void aniadirProducto(Producto producto) {
        productosVendidos.add(producto);
        totalVenta = totalVenta + producto.getPrecioUnitario();
    }

    public double getTotalVenta() {
        return totalVenta;
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
    
    
}

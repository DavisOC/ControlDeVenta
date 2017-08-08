/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Publica
 */
public class Producto {

    private String nombre;
    private String descripcion;
    private long codigo;
    private double precioUnitario;
    private Date fechaDeVencimiento;
    private int cantidad;

    public Producto(String nombre, long codigo, double precioUnitario, String fechaDeVencimiento) throws ParseException {

        this.nombre = nombre;
        this.codigo = codigo;
        this.precioUnitario = precioUnitario;
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaDeVencimiento = formater.parse(fechaDeVencimiento);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }
}

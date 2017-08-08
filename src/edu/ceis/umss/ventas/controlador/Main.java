/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.controlador;

import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.modelo.Producto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        ContenedorDeProductos contenedor = new ContenedorDeProductos();
        Producto lataTaqinia = new Producto("Yougurt", 12421354235L, 7.50D, "10/10/2017");
        Producto cervezaTauina = new Producto("Pepsi de litro", 1234565748L, 15.0D, "20/12/2018");
        Producto lataPacenia = new Producto("Coca cola en lata ", 123456789L, 7.0D, "15/11/2018");
        Producto cerveElHinca = new Producto("Cerveza el Hinca de litro", 12345678L, 15.5D, "05/05/2018");
        Producto cervhenini = new Producto("Cerveza Heiniken", 301300256L, 18.0D, "01/01/2019");
        Producto pepsi = new Producto("Pepsi de 3 litros", 3654789L, 11.0D, "08/08/2020");

        contenedor.aniadirProducto(lataTaqinia);
        contenedor.aniadirProducto(cervezaTauina);
        contenedor.aniadirProducto(lataPacenia);
        contenedor.aniadirProducto(cerveElHinca);
        contenedor.aniadirProducto(cervhenini);
        contenedor.aniadirProducto(pepsi);
        Date fechaActual = new Date();

        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formater.format(fechaActual);
        System.out.println("date " + fecha);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.controlador;

import edu.ceis.umss.ventas.modelo.ContenedorDeProductos;
import edu.ceis.umss.ventas.modelo.Producto;
import edu.ceis.umss.ventas.vista.VentanaPrincipal;

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
        Date fechaActual = new Date();
        ContenedorDeProductos contenedor = new ContenedorDeProductos();
        Producto chicolac = new Producto("Chicolac", 12421354235L, 0.50, "01/01/2013");
        Producto mani = new Producto("Mani", 1234565748L, 5.0D, "01/01/2013");
        Producto cocacola = new Producto("Coca Cola", 123456789L, 5.0D, "01/01/2013");
        Producto leche = new Producto("Leche", 12345678L, 5.5D, "01/01/2013");
        Producto chizito = new Producto("Chizito", 301300256L, 2.0D, "01/01/2013");
        Producto galleta = new Producto("Galleta", 3654789L, 3.0D, "01/01/2013");
        Producto yogurt = new Producto("Yogurt", 2587948L, 10.5D, "01/01/2013");
        Producto masticable = new Producto("Masticable", 36521458L, 0.1D, "01/01/2013");

        contenedor.aniadirProducto(chicolac);
        contenedor.aniadirProducto(mani);
        contenedor.aniadirProducto(cocacola);
        contenedor.aniadirProducto(leche);
        contenedor.aniadirProducto(chizito);
        contenedor.aniadirProducto(galleta);
        contenedor.aniadirProducto(yogurt);
        contenedor.aniadirProducto(masticable);
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formater.format(fechaActual);
        System.out.println("date " + fecha);
        VentanaPrincipal ventanaControlDeVentas = new VentanaPrincipal(contenedor);
    }
}

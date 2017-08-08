/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author FUNDACIONJALA\jhonatan^mamani
 */
public class Principal {

    public static void main(String[] args) throws ParseException {
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
        Date fechaActual = new Date();

        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formater.format(fechaActual);
        System.out.println("date " + fecha);

        ListaDeVentas listaDeVentas = new ListaDeVentas(fechaActual, contenedor);

        Transaccion transaccion = new Transaccion("123T", fechaActual);
        transaccion.aniadirProducto(yogurt);
        transaccion.aniadirProducto(yogurt);
        transaccion.aniadirProducto(galleta);
        transaccion.aniadirProducto(galleta);
        Transaccion transaccion1 = new Transaccion("124T", fechaActual);
        transaccion1.aniadirProducto(yogurt);
        transaccion1.aniadirProducto(yogurt);
        transaccion1.aniadirProducto(galleta);
        transaccion1.aniadirProducto(masticable);

        listaDeVentas.aniadirTransaccion(transaccion);
        listaDeVentas.aniadirTransaccion(transaccion1);
        try {
        String ruta = Principal.class.getResource("salida").getPath();

        } catch(NullPointerException exception) {
            System.out.println("no hay recurso = [" + exception + "]");
            throw  new IllegalArgumentException();
        }
//        System.out.println("Ruta : " + ruta);
    }
}

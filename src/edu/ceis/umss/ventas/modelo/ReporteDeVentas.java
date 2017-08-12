/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author
 */
public class ReporteDeVentas {

    private static List<Transaccion> reporteDeVentas;
    private ListaDeVentas listaDeVentas;
    private int count;

    public ReporteDeVentas() {
        count = 0;
    }

    public ListaDeVentas getListaDeVentas() {
        return listaDeVentas;
    }

    public void setListaDeVentas(ListaDeVentas listaDeVentas) {
        reporteDeVentas = listaDeVentas.getListaDeVentas();
        this.listaDeVentas = listaDeVentas;
    }

    public static void setReporteDeVentas(List<Transaccion> reporteDeVentas) {
        ReporteDeVentas.reporteDeVentas = reporteDeVentas;
    }

    public File generarArchivo(String ruta) throws ParseException {
        count++;
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("archivo-" + count + ".txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < 1; i++) {
                Transaccion transaccion10 = reporteDeVentas.get(0);
                pw.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                pw.println("------------------------------- Transaccion " + transaccion10.getCodigoDeTransaccion() + "-------------------------------");
                pw.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                pw.println(" Nombre del Producto  |  Codigo  |  Precio Unitario  |  Fecha de Vencemiento  ");
                for (Producto producto : transaccion10.getProductosVendidos()) {
                    pw.printf("%-25s %-15s %-15s %-10s %n", "    "
                                    + producto.getNombre(),
                            producto.getCodigo(),
                            producto.getPrecioUnitario(),
                            producto.getFechaDeVencimiento().toString());
                    pw.println("  ");
                }
                pw.println(" Costo Total de la Transaccion :  " + transaccion10.getTotalVenta() + " Bs");
                pw.println("");
            }

        } catch (IOException e) {
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
            }
        }
        return new File(ruta);

    }

}

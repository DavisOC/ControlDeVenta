/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista;

import edu.ceis.umss.ventas.modelo.*;
import edu.ceis.umss.ventas.vista.modelo.ModeloTablaDeVentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author
 */
public class PanelBusquedaYseleccion extends JPanel {

    ReporteDeVentas reporte;
    private JTable tablero;
    private JButton vedido;
    private ImageIcon logotipo;
    private JPanel panelLogo;
    private ModeloTablaDeVentas modelo;
    private ListaDeVentas ventas;
    private JTextField texto;
    private HashMap<Long, Producto> contiene;
    private JLabel precio;

    public PanelBusquedaYseleccion(ListaDeVentas ventas, ContenedorDeProductos contenedor) {
        this.contiene = (HashMap<Long, Producto>) contenedor.getContenedorDeProducto();
        this.ventas = ventas;
        reporte = new ReporteDeVentas();
        iniciarComponentes();
        aderirComponentes();
        setVisible(true);
    }

    private void iniciarComponentes() {
        texto = new JTextField(20);
        texto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                vender();
            }
        });
        texto.setFont(new Font("SansSerif", Font.PLAIN, 15));
        vedido = new JButton(new AbstractAction("Generar Transaccion") {

            @Override
            public void actionPerformed(ActionEvent e) {
                ventaTerminada();
            }
        });
        vedido.setFont(new Font("SansSerif", Font.PLAIN, 15));
        logotipo = new ImageIcon(VentanaPrincipal.class.getResource("imagenes/Penguins.jpg"));
        panelLogo = new JPanel(new BorderLayout(10, 10));
        modelo = new ModeloTablaDeVentas(ventas);
        tablero = new JTable(modelo);
        tablero.setRowHeight(40);
        Dimension dim = new Dimension(20, 20);
        tablero.setIntercellSpacing(new Dimension(dim));
        tablero.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tablero.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
    }

    private void ventaTerminada() {
        Transaccion transaccion = new Transaccion("123", new Date());
        transaccion.setProductosVendidos(modelo.getListaDeVentas());
        ventas.aniadirTransaccion(transaccion);
        reporte.setListaDeVentas(ventas);
        if (!transaccion.getProductosVendidos().isEmpty()) {
            try {
                reporte.generarArchivo("");
                ventas.eliminarTransaccion(transaccion);
                ReporteDeVentas.setReporteDeVentas(new ArrayList<Transaccion>());
                reporte.setListaDeVentas(ventas);
            } catch (ParseException ex) {
                Logger.getLogger(PanelBusquedaYseleccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        modelo.setLista(new ArrayList<Producto>());
        modelo.setPrecio(0.0);
        tablero.updateUI();
    }

    public void vender() {
        try {
            String entrada = texto.getText();
            if (contiene.containsKey(Long.parseLong(entrada))) {
                Producto producto = contiene.get(Long.parseLong(entrada));
                modelo.aniadirVenta(producto);
                producto.setCantidad(producto.getCantidad() - 1);
                tablero.updateUI();
                texto.setText("");
                updateUI();
                precio.setText("Precio Total es : " + getPrecioTotalDeTransaccion());
                precio.updateUI();
            } else {
                JOptionPane.showMessageDialog(this, "El código del producto no esta registrado  \n "
                        + "             Registre el Producto");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo de texto no corresponde a un número");
        }
    }

    private void aderirComponentes() {
        precio = new JLabel("Precio Total es : ");
        precio.setFont(new Font("SansSerif", Font.PLAIN, 15));
//        precio = new PrecioTotal("Precio total es : ", this);
        setLayout(new BorderLayout());
        panelLogo.add(new JLabel());
        panelLogo.add(new JPanel(), BorderLayout.CENTER);
        JPanel vendedor = new JPanel(new BorderLayout(10, 10));
        vendedor.setLayout(new BoxLayout(vendedor, 1));
        JLabel label = new JLabel("Busqueda por codigo QR/Barras: ");
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        vendedor.add(label);
        vendedor.add(texto);
        vendedor.add(vedido);
        panelLogo.add(vendedor, BorderLayout.PAGE_END);
        JPanel total = new JPanel();
        total.setLayout(new BorderLayout());
        total.add(new JScrollPane(tablero), BorderLayout.CENTER);

        total.add(precio, BorderLayout.PAGE_END);

        add(total, BorderLayout.CENTER);
        add(panelLogo, BorderLayout.LINE_END);

    }

    public String getPrecioTotalDeTransaccion() {
        double total = modelo.getPrecio();
        return Double.toString(total);
    }
}

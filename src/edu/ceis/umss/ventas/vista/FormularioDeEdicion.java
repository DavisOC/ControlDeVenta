/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.vista;

import edu.ceis.umss.ventas.controlador.EditarProductoAccion;
import edu.ceis.umss.ventas.modelo.Producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author
 */
public class FormularioDeEdicion extends JPanel {

    private final Producto producto;
    private final EditarProductoAccion editar;
    private JTextField codigoEntrada;
    private JTextField nombreEntrada;
    private JTextField precioEntrada;
    private JFormattedTextField fechaEntrada;

    public FormularioDeEdicion(EditarProductoAccion editar, Producto producto) {
        this.producto = producto;
        this.editar = editar;
        inicializando();
    }

    private void inicializando() {
        setLayout(new BorderLayout());
        JPanel contenido = new JPanel(new GridLayout(0, 2));
        JPanel labels = new JPanel(new GridLayout(4, 1));
        JPanel entradas = new JPanel(new GridLayout(4, 1));

        JLabel label1 = new JLabel("  Codigo");
        label1.setFont(new Font("SansSerif", Font.ITALIC, 15));
        JLabel label2 = new JLabel("  Nombre");
        label2.setFont(new Font("SansSerif", Font.ITALIC, 15));
        JLabel label3 = new JLabel("  Precio");
        label3.setFont(new Font("SansSerif", Font.ITALIC, 15));
        JLabel label4 = new JLabel("  Fecha de Ven.");
        label4.setFont(new Font("SansSerif", Font.ITALIC, 15));

        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        codigoEntrada = new JTextField(String.valueOf(producto.getCodigo()), 50);
        codigoEntrada.setFont(new Font("SansSerif", Font.PLAIN, 15));
        nombreEntrada = new JTextField(producto.getNombre(), 50);
        nombreEntrada.setFont(new Font("SansSerif", Font.PLAIN, 15));
        precioEntrada = new JTextField(String.valueOf(producto.getPrecioUnitario()), 50);
        precioEntrada.setFont(new Font("SansSerif", Font.PLAIN, 15));
        fechaEntrada = new JFormattedTextField(new SimpleDateFormat("dd-MM-yyyy"));
        fechaEntrada.setValue(producto.getFechaDeVencimiento());
        fechaEntrada.setFont(new Font("SansSerif", Font.PLAIN, 15));

        entradas.add(codigoEntrada);
        entradas.add(nombreEntrada);
        entradas.add(precioEntrada);
        entradas.add(fechaEntrada);

        contenido.add(labels);
        contenido.add(entradas);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        botonGuardar.setFont(new Font("SansSerif", Font.PLAIN, 15));
        botones.add(botonGuardar);
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editar.cancelar();
            }
        });
        botonCancelar.setFont(new Font("SansSerif", Font.PLAIN, 15));
        botones.add(botonCancelar);
        add(contenido, BorderLayout.CENTER);
        add(botones, BorderLayout.PAGE_END);
    }

    private void guardar() {
        try {
            if (estanVacio()) {
                JOptionPane.showMessageDialog(this, "El campo de texto está vacío");
            } else if (!esDigito(codigoEntrada.getText())) {
                JOptionPane.showMessageDialog(this, "El campo de texto no corresponde a un digito");
            } else if (!esDecimal(precioEntrada.getText())) {
                JOptionPane.showMessageDialog(this, "El campo de texto no corresponde a un decimal");
            } else {
                editar.editar();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número es demasiado largo");
        }
        setVisible(true);
    }

    private boolean esDecimal(String entrada) {
        Pattern pattern = Pattern.compile("^-?\\d*\\.\\d+$|^-?\\d+$");
        return pattern.matcher(entrada).find();
    }

    private boolean esDigito(String entrada) {
        Pattern pattern = Pattern.compile("^-?\\d+$");
        return (entrada != null) && pattern.matcher(entrada).matches();
    }

    private boolean estanVacio() {
        return estaVacio(codigoEntrada.getText()) || estaVacio(nombreEntrada.getText())
                || estaVacio(precioEntrada.getText()) || estaVacio(fechaEntrada.getValue().toString());
    }

    private boolean estaVacio(String entrada) {
        return entrada.trim().isEmpty();
    }

    public Producto construirProducto() {
        producto.setCodigo(Long.parseLong(codigoEntrada.getText()));
        producto.setNombre(nombreEntrada.getText());
        producto.setPrecioUnitario(Double.valueOf(precioEntrada.getText()));
        producto.setFechaDeVencimiento((Date) fechaEntrada.getValue());
        return producto;
    }
}

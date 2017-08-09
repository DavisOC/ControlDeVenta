package edu.ceis.umss.ventas.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends JFrame {

    private JTabbedPane tabs;

    public VentanaPrincipal() {
        setSize(1440, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabs = new JTabbedPane();
        tabs.add("Registro de productos", new PanelRegistroDeProducto());
        tabs.add("Busqueda y seleccion", null);
        tabs.add("Almacen", new JPanel());
        tabs.setMnemonicAt(0, KeyEvent.VK_L);
        tabs.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        getContentPane().add(tabs);
        setVisible(true);
    }
}

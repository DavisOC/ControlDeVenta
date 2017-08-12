/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.controlador;

import edu.ceis.umss.ventas.modelo.Producto;
import edu.ceis.umss.ventas.vista.FormularioDeEdicion;
import edu.ceis.umss.ventas.vista.modelo.ModeloDeRegistro;

import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.NAME;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 *
 * @author
 */
public class EditarProductoAccion extends AbstractAction implements ListSelectionListener {

    private final ModeloDeRegistro modelo;
    private final JTable tablero;
    private JDialog dialogo;
    private FormularioDeEdicion form;

    public EditarProductoAccion(JTable tablero, ModeloDeRegistro modelo) {
        this.modelo = modelo;
        this.tablero = tablero;

        putValue(NAME, "Editar");
        tablero.getSelectionModel().addListSelectionListener(this);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E,
                Event.ALT_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(Action.SMALL_ICON, new ImageIcon(EditarProductoAccion.class.getResource("Iconos/openIcon.ico")));
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int seleccionado = tablero.getSelectedRow();
        motrarDialogo(modelo.getProductoDe(seleccionado));
    }

    public void motrarDialogo(Producto producto) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        dialogo = new JDialog();
        dialogo.setTitle(getValue(NAME).toString());
        form = new FormularioDeEdicion(this, producto);
        dialogo.getLayeredPane().getComponent(1).setFont(new Font("Lucida", Font.PLAIN, 22));
        dialogo.setSize(400, 300);
        dialogo.getContentPane().add(form);
        dialogo.setModal(true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }

    public void save() {
        form.construirProducto();
        modelo.fireTableDataChanged();
        cancelar();
    }

    public void cancelar() {
        dialogo.setVisible(false);
        dialogo = null;

    }

    public void guardar() {
        Producto producto = form.construirProducto();

        modelo.agregar(producto);
        tablero.updateUI();
        cancelar();

    }

    public void editar() {
        Producto producto = form.construirProducto();

        modelo.editar(producto);
        tablero.updateUI();
        cancelar();

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        boolean estaAlgunaFilaSeleccionada = tablero.getSelectedRow() > -1;
        setEnabled(estaAlgunaFilaSeleccionada);
    }
}

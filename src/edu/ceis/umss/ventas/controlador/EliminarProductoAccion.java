/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.controlador;

import edu.ceis.umss.ventas.vista.modelo.ModeloDeRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * 
 * @author 
 */
public class EliminarProductoAccion extends AbstractAction implements ListSelectionListener {

    private final ModeloDeRegistro modelo;
    private final JTable tablero;

    public EliminarProductoAccion(ModeloDeRegistro modelo, JTable tablero) {
        super("Eliminar");
        this.modelo = modelo;
        this.tablero = tablero;
        tablero.getSelectionModel().addListSelectionListener(this);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D,
                KeyEvent.CTRL_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        int inidiceSeleccionado = tablero.getSelectedRow();
        if (inidiceSeleccionado > -1) {
            if (JOptionPane.showConfirmDialog(tablero,
                    "¿Estas seguro de Eliminar el Producto?", "Eliminar",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE) == 0) {
                modelo.remove(inidiceSeleccionado);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
        }
        boolean estaAlgunaFilaSeleccionada = tablero.getSelectedRow() > -1;
        setEnabled(estaAlgunaFilaSeleccionada);
    }
}

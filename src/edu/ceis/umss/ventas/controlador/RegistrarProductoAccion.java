/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ceis.umss.ventas.controlador;

import edu.ceis.umss.ventas.modelo.Producto;
import edu.ceis.umss.ventas.vista.FormularioDelProducto;
import edu.ceis.umss.ventas.vista.modelo.ModeloDeRegistro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.KeyStroke;

/**
 *
 * @author
 */
public class RegistrarProductoAccion extends AbstractAction {

    private final ModeloDeRegistro modelo;
    private JDialog dialogo;
    private FormularioDelProducto form;
    private final JTable tablero;

    public RegistrarProductoAccion(ModeloDeRegistro modelo, JTable tablero) {
        super("Registrar");
        this.modelo = modelo;
        this.tablero = tablero;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A,
                KeyEvent.CTRL_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Producto producto = null;
        try {
            producto = new Producto("producto", 0, 0.0, "12/12/2000");
        } catch (ParseException ex) {
            Logger.getLogger(RegistrarProductoAccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        motrarDialogo(producto);

    }

    protected ModeloDeRegistro getModelo() {
        return modelo;
    }

    protected FormularioDelProducto getForm() {
        return form;
    }

    public void motrarDialogo(Producto producto) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        dialogo = new JDialog();
        dialogo.setTitle(getValue(NAME).toString());
        form = new FormularioDelProducto(this, producto);
        dialogo.getContentPane().add(form);
        dialogo.getLayeredPane().getComponent(1).setFont(new Font("Lucida", Font.PLAIN, 22));
        dialogo.setSize(400, 300);
        dialogo.setModal(true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }

    public void guardar() {
        Producto producto = form.construirProducto();

        modelo.agregar(producto);
        tablero.updateUI();
        cancelar();
    }

    public void cancelar() {
        dialogo.setVisible(false);
        dialogo = null;

    }
}

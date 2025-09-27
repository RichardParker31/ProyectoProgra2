/*
 Chimaltenango, 26 de septiembre de 2025
Programador: Ricado Noj
Descripción: Proyecto segundo Sementre
 */
package Tienda;

import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Crud_Producto crud = new Crud_Producto();

        // Login simple antes de abrir GUI
        String usuario = JOptionPane.showInputDialog("Usuario:");
        String contraseña = JOptionPane.showInputDialog("Contraseña:");

        if(usuario.equals("admin") && contraseña.equals("1234")) {
            SwingUtilities.invokeLater(() -> {
                AdminGUI gui = new AdminGUI(crud);
                gui.setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminGUI extends JFrame {
    private Crud_Producto crud;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private JTextField tfCodigo, tfNombre, tfPrecio, tfCantidad, tfBuscarCodigo;

    public AdminGUI(Crud_Producto crud) {
        this.crud = crud;
        setTitle("Administrador - Inventario");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio", "Cantidad"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        panel.add(scroll, BorderLayout.CENTER);

        // Panel inferior para acciones
        JPanel panelAcciones = new JPanel();
        panelAcciones.setLayout(new GridLayout(2, 5, 5, 5));

        tfCodigo = new JTextField();
        tfNombre = new JTextField();
        tfPrecio = new JTextField();
        tfCantidad = new JTextField();
        tfBuscarCodigo = new JTextField();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        // Panel superior para entradas de datos
        JPanel panelDatos = new JPanel(new GridLayout(3, 4, 10, 10)); // 3 filas, 4 columnas, con espacios de 10px

        panelDatos.add(new JLabel("Código:"));
        tfCodigo = new JTextField();
        panelDatos.add(tfCodigo);

        panelDatos.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        panelDatos.add(tfNombre);

        panelDatos.add(new JLabel("Precio:"));
        tfPrecio = new JTextField();
        panelDatos.add(tfPrecio);

        panelDatos.add(new JLabel("Cantidad:"));
        tfCantidad = new JTextField();
        panelDatos.add(tfCantidad);

        panelDatos.add(new JLabel("Código a buscar:"));
        tfBuscarCodigo = new JTextField();
        panelDatos.add(tfBuscarCodigo);

        // agregamos celdas vacías para mantener la cuadrícula
        panelDatos.add(new JLabel(""));
        panelDatos.add(new JLabel(""));

        panel.add(panelDatos, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        panel.add(panelBotones, BorderLayout.SOUTH);

        // Listeners botones
        btnAgregar.addActionListener(e -> agregarProducto());
        btnMostrar.addActionListener(e -> mostrarProductos());
        btnBuscar.addActionListener(e -> buscarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
    }

    private void agregarProducto() {
        try {
            String codigo = tfCodigo.getText();
            String nombre = tfNombre.getText();
            double precio = Double.parseDouble(tfPrecio.getText());
            int cantidad = Integer.parseInt(tfCantidad.getText());

            Producto p = new Producto(codigo, nombre, precio, cantidad);
            crud.crearProducto(p);
            JOptionPane.showMessageDialog(this, "Producto agregado");
            limpiarCampos();
            mostrarProductos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio o cantidad inválidos");
        }
    }

    private void mostrarProductos() {
        modeloTabla.setRowCount(0);
        List<Producto> lista = crud.leerProductos();
        for (Producto p : lista) {
            modeloTabla.addRow(new Object[]{p.getCodProducto(), p.getNombreProducto(), p.getPreecioProducto(), p.getCantidadProducto()});
        }
    }

    private void buscarProducto() {
        String codigo = tfBuscarCodigo.getText();
        List<Producto> lista = crud.leerProductos();
        boolean encontrado = false;
        modeloTabla.setRowCount(0);

        for (Producto p : lista) {
            if (p.getCodProducto().equalsIgnoreCase(codigo)) {
                modeloTabla.addRow(new Object[]{p.getCodProducto(), p.getNombreProducto(), p.getPreecioProducto(), p.getCantidadProducto()});
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado");
        }
    }

    private void actualizarProducto() {
        try {
            String codigo = tfCodigo.getText();
            String nombre = tfNombre.getText();
            int cantidad = Integer.parseInt(tfCantidad.getText());

            crud.actualizarProducto(codigo, nombre, cantidad);
            JOptionPane.showMessageDialog(this, "Producto actualizado");
            limpiarCampos();
            mostrarProductos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida");
        }
    }

    private void eliminarProducto() {
        String codigo = tfCodigo.getText();
        crud.eliminarProducto(codigo);
        JOptionPane.showMessageDialog(this, "Producto eliminado");
        limpiarCampos();
        mostrarProductos();
    }

    private void limpiarCampos() {
        tfCodigo.setText("");
        tfNombre.setText("");
        tfPrecio.setText("");
        tfCantidad.setText("");
        tfBuscarCodigo.setText("");
    }
}

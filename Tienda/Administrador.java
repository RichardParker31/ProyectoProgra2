/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

/**
 *
 * @author ricar
 */
import java.util.Scanner;

public class Administrador {
    private Crud_Producto crud;

    public Administrador(Crud_Producto crud) {
        this.crud = crud;
    }

    public void login(Scanner sc) {
        System.out.println("=== LOGIN ADMINISTRADOR ===");
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        if(usuario.equals("admin") && contraseña.equals("1234")) {
            System.out.println("¡Bienvenido Administrador!");
            mostrarMenu(sc);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1:
                    System.out.print("Código Producto: ");
                    String codigo = sc.nextLine();
                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio del producto: ");
                    double precio = sc.nextDouble();
                    sc.nextLine(); // limpiar buffer
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    Producto p = new Producto(codigo, nombre, precio, cantidad);
                    crud.crearProducto(p);
                    break;

                case 2:
                    crud.mostrarProductos();
                    break;

                case 3:
                    System.out.print("Ingrese código del producto a buscar: ");
                    String codBuscar = sc.nextLine();
                    crud.buscarProductos(codBuscar);
                    break;

                case 4:
                    System.out.print("Ingrese el código del producto a actualizar: ");
                    String codActualizar = sc.nextLine();
                    System.out.print("Nuevo nombre (dejar vacío si no cambia): ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = sc.nextInt();
                    sc.nextLine(); // limpiar buffer
                    crud.actualizarProducto(codActualizar, nuevoNombre, nuevaCantidad);
                    break;

                case 5:
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    String codEliminar = sc.nextLine();
                    crud.eliminarProducto(codEliminar);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema de administración...");
                    break;

                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }

        } while(opcion != 0);
    }
}


/*
Chimaltenango, 26 de septiembre de 2025
Programador: Ricado Noj
Descripción: Proyecto segundo Sementre
 */
package Tienda;

import java.io.BufferedWriter;
import java.io.*;
import java.util.*;
public class Crud_Producto {
    private static final String FILE_NAME = "productos.txt";
    
    //Crear producto
    public void crearProducto(Producto producto){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))){
            bw.write(producto.toString());
            bw.newLine();
            System.out.println("Producto agregado: " +producto.getNombreProducto());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    //Mostrar Productos
    public void mostrarProductos(){
        List<Producto> lista = leerProductos();
        if (lista.isEmpty()){
            System.out.println("No hay productos en el invertario.");
        } else {
            System.out.println("Lista de productos:");
            for (Producto producto : lista){
                 System.out.println("Codigo: " + producto.getCodProducto() +
                                    "| Nombre: " + producto.getNombreProducto() + 
                                    "| Cantidad: " + producto.getCantidadProducto() + 
                                    "| Precio: " + producto.getPreecioProducto());
            }
        }
    }
    
    //Buscar producto 
    public void buscarProductos(String codigo){
        List<Producto> lista = leerProductos();
        boolean encontrado = false;
        for (Producto producto : lista){
            if (producto.getCodProducto().equalsIgnoreCase(codigo)){
                System.out.println("Producto encontrado: " +
                                    producto.getNombreProducto() + 
                                    "| Cantidad: " + producto.getCantidadProducto() + 
                                    "| Precio: " + producto.getPreecioProducto());
                encontrado = true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("El producto con código; " + codigo + "no existe");
        }
    }
    
    //Eliminar Proucto
    public void eliminarProducto(String codigo){
        List<Producto> lista = leerProductos();
        boolean eliminado = false;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Producto producto : lista){
                if (!producto.getCodProducto().equalsIgnoreCase(codigo)){
                    bw.write(producto.toString());
                    bw.newLine();
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (eliminado){
            System.out.println("Producto elimindao con éxito.");
        } else {
            System.out.println("No se encontró un producto con ese codigo");
        }
    }
    
    //Actualizar producto (nombre y cantidad)
    public void actualizarProducto(String codigo, String nuevoNombre, int nuevaCantidad){
        List<Producto> lista = leerProductos();
        boolean actualizado = false;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Producto producto : lista){
                if(producto.getCodProducto().equals(codigo)){
                    //Mantiene precio, actualiza nombre y cantidad del producto
                    String nombreFinal = (nuevoNombre != null && !nuevoNombre.isEmpty()) ? nuevoNombre : producto.getNombreProducto();
                    Producto actualizadoProducto = new Producto(codigo, nombreFinal, producto.getPreecioProducto(), nuevaCantidad);
                    bw.write(actualizadoProducto.toString());
                    actualizado = true;
                } else {
                    bw.write(producto.toString());
                }
                bw.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        
        if (actualizado){
            System.out.println("Producto actualizado con éxito.");
        } else {
            System.out.println("El producto con código " + codigo + "no existe");
        }
    }
    
    //Leer productos desde archivo
    public List<Producto> leerProductos() {
        List<Producto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String cod = datos[0].trim();
                    String nombre = datos[1].trim();
                    double precio = Double.parseDouble(datos[2].trim());
                    int cantidad = Integer.parseInt(datos[3].trim());
                    lista.add(new Producto(cod, nombre, precio, cantidad));
                }
            }
        } catch (IOException e) {
            // Si no existe el archivo, devuelve lista vacía
        }
        return lista;
    }
} 
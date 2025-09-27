/*
 Chimaltenango, 26 de septiembre de 2025
Programador: Ricado Noj
Descripción: Proyecto segundo Sementre
 */
package Tienda;

public class Producto {
    private String codProducto;
    private String nombreProducto;
    private double preecioProducto;
    private int cantidadProducto;
    
    //Constructor
    public Producto(String codProducto, String nombreProducto, double preecioProducto, int cantidadProducto) {
        this.codProducto = codProducto;
        this.nombreProducto = nombreProducto;
        this.preecioProducto = preecioProducto;
        this.cantidadProducto = cantidadProducto;
    }
    //Getter y Setters
    public String getCodProducto() { return codProducto; }
    public void setCodProducto(String codProducto) { this.codProducto = codProducto; }
    
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    
    public double getPreecioProducto() { return preecioProducto; }
    public void setPreecioProducto(double preecioProducto) { this.preecioProducto = preecioProducto; }
    
    public int getCantidadProducto() { return cantidadProducto; }
    public void setCantidadProducto(int cantidadProducto) { this.cantidadProducto = cantidadProducto; }

    //Representación en texto
    @Override
    public String toString() {
        return codProducto + ", " + nombreProducto + ", " + preecioProducto + ", " + cantidadProducto;
    }
   
}//Fin public class

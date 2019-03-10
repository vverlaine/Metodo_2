package Model;

public class Producto {
    private String Nombre;
    private int precio;

    public Producto(String nombre, int precio) {
        Nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}

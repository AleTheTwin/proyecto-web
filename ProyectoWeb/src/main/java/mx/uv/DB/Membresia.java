package mx.uv.DB;

public class Membresia {
    private String id;
    private String descripcion;
    private double precio;

    public Membresia(String id, String descripcion, double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Membresia: " + this.getId() + ", Descripcion: " + this.getDescripcion() + ", Precio: " + this.getPrecio();
    }
}

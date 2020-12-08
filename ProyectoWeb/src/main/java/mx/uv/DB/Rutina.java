package mx.uv.DB;

public class Rutina {
    private String id;
    private String descripcion;

    public Rutina(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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
        return "Rutina: " + this.getId() + ", Descripcion: " + this.getDescripcion();
    }
}

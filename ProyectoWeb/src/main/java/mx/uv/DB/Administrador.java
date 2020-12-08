package mx.uv.DB;

public class Administrador extends Usuario {
    private String nombreA;

    public Administrador(String correo, String password, String nombreA) {
        super(correo, password);
        this.nombreA = nombreA;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombrA(String nombreA) {
        this.nombreA = nombreA;
    }


    public String toString() {
        return "Nombre: " + this.getNombreA() + ", " + super.toString();
    }
}

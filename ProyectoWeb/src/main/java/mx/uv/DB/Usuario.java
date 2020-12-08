package mx.uv.DB;

public class Usuario {
    private String correo;
    private String password;

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String toString() {
        return "Correo: " +  this.getCorreo();
    }
}

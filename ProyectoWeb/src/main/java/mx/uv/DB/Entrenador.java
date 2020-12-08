package mx.uv.DB;

public class Entrenador extends Usuario {
    private String nombreE;
    private String tipoE;

    public Entrenador(String correo, String password, String nombreE, String tipoE) {
        super(correo, password);
        this.nombreE = nombreE;
        this.tipoE = tipoE;
    }

    public String getTipoE() {
        return tipoE;
    }

    public void setTipoE(String tipoE) {
        this.tipoE = tipoE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }


    @Override
    public String toString() {
        return "Entrenador: " + this.getNombreE() + ", " + super.toString() + ", Tipo de entrenador: " + this.getTipoE();
    }
}

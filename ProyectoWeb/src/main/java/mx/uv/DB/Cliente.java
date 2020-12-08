package mx.uv.DB;

public class Cliente extends Usuario {
    private String nombreC;
    private int edad;
    private boolean sexo;
    private String tipoCliente;

    public Cliente(String correo, String password, String nombreC, int edad, boolean sexo, String tipoCliente) {
        super(correo, password);
        this.nombreC = nombreC;
        this.edad = edad;
        this.sexo = sexo;
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

   

    public String toString() {
        String sex = "Mujer";
        if(this.isSexo()) {
            sex = "Hombre";
        }
        return "Cliente:" + this.getNombreC() + ", " + super.toString() + ", Edad: " + this.getEdad() + ", Sexo: " + sex + ", tipo de cliente: " + this.getTipoCliente();
    }

}

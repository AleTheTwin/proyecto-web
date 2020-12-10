package mx.uv.DB;

public class CodigoRandom {
    public static final String NUMEROS = "0123456789";
 
  public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
  public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
  public static final String ESPECIALES = "ñÑ";
 
  public static String obtenerCodigo() {
    return CodigoRandom.obtenerCodigo(5);
  }
 
  public static String obtenerCodigo(int longitud) {
    return obtenerCodigo(NUMEROS + MAYUSCULAS + MINUSCULAS, longitud);
  }
 
  public static String obtenerCodigo(String clave, int longitud) {
    String codigo = "";
    for (int i = 0; i < longitud; i++) {
      codigo+=(clave.charAt((int)(Math.random() * clave.length())));
    }
    return codigo;
  }
}

package mx.uv.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDB {
  private static String url = "jdbc:mysql://gimnasio-web.cft2hvdqqkde.us-east-1.rds.amazonaws.com/gimnasio";
  private static String username = "admin";
  private static String password = "sistemasweb";
//   private static String url = "jdbc:mysql://127.0.0.1:3306/gimnaiso?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
//   private static String username = "root";
//   private static String password = "071419";
  private static String driverName = "com.mysql.cj.jdbc.Driver";
  
  private static Connection conexion = null;

  public  Connection getConexion(){
      
     try{
          Class.forName(driverName);
          conexion = DriverManager.getConnection(url, username, password);
          System.out.println("Sucess Connection");
      } catch(SQLException e){
          e.printStackTrace();
          System.out.println("Failed to create Connection");
      } catch(ClassNotFoundException e){
          System.out.println("Driver not found");
      } 
      return conexion;
  }

}

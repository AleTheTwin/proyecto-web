package mx.uv.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EntrenadorDAO implements DAO {

    @Override
    public ArrayList<Object> readAll() {
        ArrayList<Object> entrenadores = new ArrayList<Object>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM entrenador");
            while(rs.next()) {
                entrenadores.add(new Entrenador(rs.getString("Correo"), rs.getString("Password"), rs.getString("NombreE"), rs.getString("tipoE")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return entrenadores;
    }

    @Override
    public Object readByIdentifier(String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Entrenador entrenador = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM entrenador where correo = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                entrenador = new Entrenador(rs.getString("Correo"), rs.getString("Password"), rs.getString("NombreE"), rs.getString("tipoE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        return entrenador;

    }

    public void crearRutina(String email, String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("insert into crea_rutina values(?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, email);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
    }

    public ArrayList<Object> getClientes(String identifier) {
        ArrayList<Object> clientes = new ArrayList<Object>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM entrenadorcliente where correo_E = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                clientes.add(new Cliente(rs.getString("Correo_C"), "passwordNotNeeded", rs.getString("NombreC"), rs.getInt("Edad"), rs.getBoolean("Sexo"), rs.getString("tipoCliente")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return clientes;
    }

    @Override
    public void update(Object objeto, String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Entrenador entrenador = (Entrenador)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("UPDATE entrenador SET correo = ?, password = ?, nombreE = ?, tipoE = ? where correo = ?");
            stmt.setString(1, entrenador.getCorreo());
            stmt.setString(2, entrenador.getPassword());
            stmt.setString(3, entrenador.getNombreE());
            stmt.setString(4, entrenador.getTipoE());
            stmt.setString(5, identifier);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    @Override
    public void create(Object objeto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Entrenador entrenador = (Entrenador)objeto;
        try {
            conn = new ConexionDB().getConexion();
            System.out.println("conexion establecida");
            stmt = conn.prepareStatement("INSERT into entrenador(correo, password, NombreE, tipoE) VALUES (?, ?, ?, ?)");
            stmt.setString(1, entrenador.getCorreo());
            stmt.setString(2, entrenador.getPassword());
            stmt.setString(3, entrenador.getNombreE());
            stmt.setString(4, entrenador.getTipoE());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Fallo en la creacion: \n");
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    @Override
    public void delete(String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("DELETE FROM entrenador WHERE Correo = ?");
            stmt.setString(1, identifier);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    
}

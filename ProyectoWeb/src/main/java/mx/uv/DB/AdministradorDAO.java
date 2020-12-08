package mx.uv.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdministradorDAO implements DAO {

    @Override
    public ArrayList<Object> readAll() {
        ArrayList<Object> administradores = new ArrayList<Object>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Administrador");
            while(rs.next()) {
                administradores.add(new Administrador(rs.getString("Correo"), rs.getString("Password"), rs.getString("NombreA")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return administradores;
    }

    @Override
    public Object readByIdentifier(String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Administrador administrador = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM administrador where correo = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                administrador = new Administrador(rs.getString("Correo"), rs.getString("Password"), rs.getString("NombreA"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        return administrador;

    }

    @Override
    public void update(Object objeto, String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Administrador administrador = (Administrador)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("UPDATE Administrador SET correo = ?, password = ?, NombreA = ? where correo = ?");
            stmt.setString(1, administrador.getCorreo());
            stmt.setString(2, administrador.getPassword());
            stmt.setString(3, administrador.getNombreA());
            stmt.setString(4, identifier);
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
        Administrador administrador = (Administrador)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("INSERT into Administrador(correo, password, NombreA) VALUES (?, ?, ?)");
            stmt.setString(1, administrador.getCorreo());
            stmt.setString(2, administrador.getPassword());
            stmt.setString(3, administrador.getNombreA());
            stmt.execute();
        } catch (Exception e) {
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
            stmt = conn.prepareStatement("DELETE FROM Administrador WHERE Correo = ?");
            stmt.setString(1, identifier);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    
}

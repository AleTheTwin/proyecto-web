package mx.uv.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RutinaDAO implements DAO {

    @Override
    public ArrayList<Object> readAll() {
        ArrayList<Object> rutinas = new ArrayList<Object>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rutina");
            while(rs.next()) {
                rutinas.add(new Rutina(rs.getString("id"), rs.getString("descripcion")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return rutinas;
    }

    public ArrayList<Object> readByCliente(String identifier) {
        ArrayList<Object> rutinas = new ArrayList<Object>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM clienterutina where correo = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                rutinas.add(new Rutina(rs.getString("id_r"), rs.getString("descripcion")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return rutinas;

    }

    @Override
    public Object readByIdentifier(String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Rutina rutina = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM rutina where id = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                rutina = new Rutina(rs.getString("id"), rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        return rutina;

    }


    @Override
    public void update(Object objeto, String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Rutina rutina = (Rutina)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("UPDATE rutina SET id = ?, descripcion = ? where id = ?");
            stmt.setString(1, rutina.getId());
            stmt.setString(2, rutina.getDescripcion());
            stmt.setString(3, identifier);
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
        Rutina rutina = (Rutina)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("INSERT into rutina(id, descripcion) VALUES (?, ?)");
            stmt.setString(1, rutina.getId());
            stmt.setString(2, rutina.getDescripcion());
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
            stmt = conn.prepareStatement("DELETE FROM rutina WHERE id = ?");
            stmt.setString(1, identifier);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    
}

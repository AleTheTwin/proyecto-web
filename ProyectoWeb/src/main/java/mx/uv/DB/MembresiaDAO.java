package mx.uv.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MembresiaDAO implements DAO {

    @Override
    public ArrayList<Object> readAll() {
        ArrayList<Object> membresias = new ArrayList<Object>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Membresia");
            while(rs.next()) {
                membresias.add(new Membresia(rs.getString("Id"), rs.getString("Descripcion"), rs.getDouble("precio")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return membresias;
    }

    @Override
    public Object readByIdentifier(String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Membresia membresia = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM membresia where id = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                membresia = new Membresia(rs.getString("Id"), rs.getString("Descripcion"), rs.getDouble("precio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        return membresia;

    }

    @Override
    public void update(Object objeto, String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Membresia membresia = (Membresia)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("UPDATE Membresia SET id = ?, Descripcion = ?, precio = ? where id = ?");
            stmt.setString(1, membresia.getId());
            stmt.setString(2, membresia.getDescripcion());
            stmt.setDouble(3, membresia.getPrecio());
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
        Membresia membresia = (Membresia)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("INSERT into Membresia(id, Descripcion, Precio) VALUES (?, ?, ?)");
            stmt.setString(1, membresia.getId());
            stmt.setString(2, membresia.getDescripcion());
            stmt.setDouble(3, membresia.getPrecio());
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
            stmt = conn.prepareStatement("DELETE FROM Membresia WHERE Id = ?");
            stmt.setString(1, identifier);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    
}

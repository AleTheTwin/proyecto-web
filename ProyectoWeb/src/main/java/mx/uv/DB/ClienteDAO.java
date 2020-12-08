package mx.uv.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO implements DAO {

    @Override
    public ArrayList<Object> readAll() {
        ArrayList<Object> clientes = new ArrayList<Object>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente");
            while(rs.next()) {
                clientes.add(new Cliente(rs.getString("Correo"), rs.getString("Password"), rs.getString("NombreC"), rs.getInt("Edad"), rs.getBoolean("Sexo"), rs.getString("tipoCliente")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        
        return clientes;
    }

    @Override
    public Object readByIdentifier(String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = null;
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("SELECT * FROM Cliente where correo = ?");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                cliente = new Cliente(rs.getString("Correo"), rs.getString("Password"), rs.getString("NombreC"), rs.getInt("Edad"), rs.getBoolean("Sexo"), rs.getString("tipoCliente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
        return cliente;

    }

    @Override
    public void update(Object objeto, String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = (Cliente)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("UPDATE Cliente SET correo = ?, password = ?, NombreC = ?, Edad = ?, Sexo = ?, tipcoCliente = ? where correo = ?");
            stmt.setString(1, cliente.getCorreo());
            stmt.setString(2, cliente.getPassword());
            stmt.setString(3, cliente.getNombreC());
            stmt.setInt(4, cliente.getEdad());
            stmt.setBoolean(5, cliente.isSexo());
            stmt.setString(6, cliente.getTipoCliente());
            stmt.setString(7, identifier);
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
        Cliente cliente = (Cliente)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("INSERT into Cliente(correo, password, NombreC, Edad, Sexo, tipoCliente) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getCorreo());
            stmt.setString(2, cliente.getPassword());
            stmt.setString(3, cliente.getNombreC());
            stmt.setInt(4, cliente.getEdad());
            stmt.setBoolean(5, cliente.isSexo());
            stmt.setString(6, cliente.getTipoCliente());
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
            stmt = conn.prepareStatement("DELETE FROM Cliente WHERE Correo = ?");
            stmt.setString(1, identifier);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    
}

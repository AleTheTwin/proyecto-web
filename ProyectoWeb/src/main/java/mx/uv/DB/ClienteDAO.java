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
            ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
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
            stmt = conn.prepareStatement("SELECT * FROM cliente where correo = ?");
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

    public void asginarRutina(String email, String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("insert into tiene_asignado_r(id_r, correoC) values(?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, email);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    @Override
    public void update(Object objeto, String identifier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = (Cliente)objeto;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("UPDATE cliente SET correo = ?, password = ?, NombreC = ?, Edad = ?, Sexo = ?, tipoCliente = ? where correo = ?");
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

    public void setMembresia(String identifiier, String membresia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("insert into paga(correo, id_m) values (?, ?)");
            stmt.setString(1, identifiier);
            stmt.setString(2, membresia);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    public void updateMembresia(String identifiier, String membresia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("update paga set id_m = ? where correo = ?");
            stmt.setString(1, membresia);
            stmt.setString(2, identifiier);
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
            stmt = conn.prepareStatement("INSERT into cliente(correo, password, NombreC, Edad, Sexo, tipoCliente) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getCorreo());
            stmt.setString(2, cliente.getPassword());
            stmt.setString(3, cliente.getNombreC());
            stmt.setInt(4, cliente.getEdad());
            stmt.setBoolean(5, cliente.isSexo());
            stmt.setString(6, cliente.getTipoCliente());
            stmt.execute();
            System.out.println("Creado");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    public void asignarEntrenadorRandom(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        ArrayList<Object> entrenadores = entrenadorDAO.readAll();
        System.out.println("tamanio: " + entrenadores.size());
        int random = numeroRandom(entrenadores.size());
        System.out.println("random: " + random);

        Entrenador entrenador = (Entrenador)entrenadores.get(random);
        
        try {
            conn = new ConexionDB().getConexion();
            stmt = conn.prepareStatement("INSERT into tiene_asignado_c(correo_c, correo_e) VALUES (?, ?)");
            stmt.setString(1, email);
            stmt.setString(2,entrenador.getCorreo());
            stmt.execute();
            System.out.println("Asignado");
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
            stmt = conn.prepareStatement("DELETE FROM cliente WHERE Correo = ?");
            stmt.setString(1, identifier);
            stmt.execute();
            System.out.println("Eliminado");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch(Exception ex) {}
        }
    }

    private int numeroRandom(int m) {
        return (int) (Math.random()*m);
    }
    
}

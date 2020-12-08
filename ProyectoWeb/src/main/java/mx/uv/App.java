package mx.uv;

import mx.uv.DB.*;

import static spark.Spark.*;

import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        

        port(getHerokuAssignedPort());
        System.out.println(getHerokuAssignedPort());
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/", (request, response) -> "Hola");
    
        post("/login", (request, response) -> {
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String email;
            String pass;
            email = peticion.get("Email").getAsString();
            pass = peticion.get("Password").getAsString();
            System.out.println("email: " + email);
            System.out.println("password: " + pass);
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = (Cliente)clienteDAO.readByIdentifier(email);
            if(cliente != null) {
                if(cliente.getPassword().equals(pass)) {
                    return email;
                }
            }
            return "0";

        });

        post("/registro", (req, res) -> {
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(req.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String email;
            String pass;
            String nombre;
            int edad;
            boolean sexo;
            String tipoCliente;
            email = peticion.get("Email").getAsString();
            pass = peticion.get("Password").getAsString();
            nombre = peticion.get("nombreC").getAsString();
            edad = Integer.parseInt(peticion.get("edad").getAsString());
            if(peticion.get("sexo").getAsString().equals("1")) {
                sexo = true;
            } else {
                sexo = false;
            }
            tipoCliente = peticion.get("tipoCliente").getAsString();

            Cliente cliente = new Cliente(email, pass, nombre, edad, sexo, tipoCliente);
            
            System.out.println(cliente);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.create(cliente);
            
            return email;

        });
        /*Cliente cliente = new Cliente("correo2@correo.com", "password", "nombreC", 16, true, "Novato");
        
        System.out.println(cliente);

        ClienteDAO cDAO= new ClienteDAO();

        //cDAO.create(cliente);
        ArrayList<Object> clientes = new ArrayList<Object>(); 
        clientes = cDAO.readAll();
        for(Object o : clientes) {
            System.out.println((Cliente)o);
        }
        cDAO.delete("correo@correo.com");
        //ArrayList<Object> clientes = new ArrayList<Object>(); 
        clientes = cDAO.readAll();
        for(Object o : clientes) {
            System.out.println((Cliente)o);
        }*/
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

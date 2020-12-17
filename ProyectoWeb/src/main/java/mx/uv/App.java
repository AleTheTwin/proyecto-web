package mx.uv;

import mx.uv.DB.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
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
            pass = Hash.getHash(pass);
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
            pass = Hash.getHash(pass);
            nombre = peticion.get("NombreC").getAsString();
            edad = Integer.parseInt(peticion.get("Edad").getAsString());
            if(peticion.get("Sexo").getAsString().equals("2")) {
                sexo = false;
            } else {
                sexo = true;
            }
            tipoCliente = peticion.get("TipoCliente").getAsString();

            Cliente cliente = new Cliente(email, pass, nombre, edad, sexo, tipoCliente);
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente clienteP = null;
            clienteP = (Cliente)clienteDAO.readByIdentifier(email);
            if(clienteP != null) {
                return "0";
            } else {
                Mail.enviarEmail(nombre, email);
                clienteDAO.create(cliente);
            }
            return email;

        });

        get("/membresias", (request, response) -> {
            Gson gson = new Gson();
            Map<String, Membresia> membresiasMap = new HashMap<>();
            MembresiaDAO membresiaDAO = new MembresiaDAO();
            ArrayList<Object> lista = membresiaDAO.readAll();
            for(Object o : lista) {
                membresiasMap.put(((Membresia)o).getId(), (Membresia)o);
            }
            
            return gson.toJson(membresiasMap.values());
        });

        get("/membresiaByEmail", (request, response) -> {
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();
            Gson gson = new Gson();

            String email = peticion.get("Email").getAsString();
            Map<String, Membresia> membresiaMap = new HashMap<>();
            MembresiaDAO membresiaDAO = new MembresiaDAO();
            //Membresia membresia = (Membresia)membresiaDAO.getByCliente();
            
            
            return gson.toJson(membresiaMap.values());
        });
        
        get("/rutinas", (request, response) -> {
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();
            
            Map<String, Object> model = new HashMap<>();
            List<Rutina> rutinas = new ArrayList<Rutina>();
            RutinaDAO rutinaDAO = new RutinaDAO();
            ArrayList<Object> objects = rutinaDAO.readAll();

            String email = peticion.get("Email").getAsString();
            for (Object o : objects ) {
                rutinas.add((Rutina)o);
            }

            model.put("rutinas", rutinas);
            model.put("email", email);
            return new ModelAndView(model, "hello.ftl"); // located in src/test/resources/spark/template/freemarker
        }, new FreeMarkerEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

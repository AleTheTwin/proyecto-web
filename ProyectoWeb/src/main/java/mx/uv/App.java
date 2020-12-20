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

        JsonParser parser = new JsonParser();
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

        

        post("/loginEntrenador", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String email;
            String pass;
            
            email = peticion.get("Email").getAsString();
            pass = peticion.get("Password").getAsString();
            pass = Hash.getHash(pass);
            EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
            Entrenador entrenador = (Entrenador)entrenadorDAO.readByIdentifier(email);
            if(entrenador != null) {
                if(entrenador.getPassword().equals(pass)) {
                    return email;
                }
            }
            return "0";

        });

        post("/registro", (req, res) -> {
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
                clienteDAO.setMembresia(email, "Free");
            }
            return email;

        });

        

        post("/actualizarCliente", (req, res) -> {
            JsonElement arbol = parser.parse(req.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String actualizar;
            String email;
            String correo;
            String pass;
            String nombre;
            int edad;
            boolean sexo;
            String tipoCliente;
            email = peticion.get("Email").getAsString();
            correo = peticion.get("Correo").getAsString();
            pass = peticion.get("Password").getAsString();
            actualizar = peticion.get("SeActualiza").getAsString();
            if(actualizar.equals("SI")) {
                pass = Hash.getHash(pass);
            }
            nombre = peticion.get("Nombre").getAsString();
            edad = Integer.parseInt(peticion.get("Edad").getAsString());
            if(peticion.get("Sexo").getAsString().equals("2")) {
                sexo = false;
            } else {
                sexo = true;
            }
            tipoCliente = peticion.get("Tipo").getAsString();

            Cliente cliente = new Cliente(correo, pass, nombre, edad, sexo, tipoCliente);
            ClienteDAO clienteDAO = new ClienteDAO();
            
            if(!correo.equals(email)) {
                Cliente consulta = null;
                consulta = (Cliente)clienteDAO.readByIdentifier(correo);
                if(consulta==null) {
                    clienteDAO.update(cliente, email);
                } else {
                    return "0";
                }
            } else {
                clienteDAO.update(cliente, email);
            }
            
            return "1";

        });

        post("/actualizarEntrenador", (req, res) -> {
            JsonElement arbol = parser.parse(req.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String actualizar;
            String email;
            String correo;
            String pass;
            String nombre;
            String tipoE;
            email = peticion.get("Email").getAsString();
            correo = peticion.get("Correo ").getAsString();
            pass = peticion.get("Password").getAsString();
            actualizar = peticion.get("SeActualiza").getAsString();
            if(actualizar.equals("SI")) {
                pass = Hash.getHash(pass);
            }
            nombre = peticion.get("Nombre").getAsString();
            tipoE = peticion.get("Tipo").getAsString();

            Entrenador entrenador = new Entrenador(correo, pass, nombre, tipoE);
            EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
            
            if(!correo.equals(email)) {
                Entrenador consulta = null;
                consulta = (Entrenador)entrenadorDAO.readByIdentifier(correo);
                if(consulta==null) {
                    entrenadorDAO.update(entrenador, email);
                } else {
                    return "0";
                }
            } else {
                entrenadorDAO.update(entrenador, email);
            }
            
            return "1";

        });

        post("/registroEntrenador", (req, res) -> {
            JsonElement arbol = parser.parse(req.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String email;
            String pass;
            String nombre;
            String tipoEntrenador;
            email = peticion.get("Email").getAsString();
            pass = peticion.get("Password").getAsString();
            pass = Hash.getHash(pass);
            nombre = peticion.get("NombreE").getAsString();
            tipoEntrenador = peticion.get("TipoEntrenador").getAsString();

            Entrenador cliente = new Entrenador(email, pass, nombre, tipoEntrenador);
            EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
            Entrenador entrenadorP = null;
            entrenadorP = (Entrenador)entrenadorDAO.readByIdentifier(email);
            if(entrenadorP != null) {
                return "0";
            } else {
                //Mail.enviarEmail(nombre, email);
                entrenadorDAO.create(cliente);
            }
            return email;

        });

        get("/membresias", (request, response) -> {
            Gson gson = new Gson();
            Map<String, Membresia> membresiasMap = new HashMap<>();
            MembresiaDAO membresiaDAO = new MembresiaDAO();
            ArrayList<Object> lista = membresiaDAO.readAll();
            int i = 0;
            for(Object o : lista) {
                membresiasMap.put(("id" + Integer.toString(i)), (Membresia)o);
                i++;
            }
            
            return gson.toJson(membresiasMap);
        });

        post("/membresiaByEmail", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();
            Gson gson = new Gson();

            String email = peticion.get("Email").getAsString();
            System.out.println(email);
            MembresiaDAO membresiaDAO = new MembresiaDAO();
            Membresia membresia = (Membresia)membresiaDAO.getByCliente(email);
            Map<String, Object> model = new HashMap<>();
            model.put("Membresia", membresia);
            
            
            return gson.toJson(membresia);
        });

        post("/getDatos", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();
            Gson gson = new Gson();

            String email = peticion.get("Email").getAsString();
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = (Cliente)clienteDAO.readByIdentifier(email);
                        
            return gson.toJson(cliente);
        });

        post("/getDatosEntrenador", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();
            Gson gson = new Gson();

            String email = peticion.get("Email").getAsString();
            EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
            Entrenador entrenador = (Entrenador)entrenadorDAO.readByIdentifier(email);
                        
            return gson.toJson(entrenador);
        });
        
        post("/rutinas", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String email = peticion.get("Email").getAsString();
            Map<String, Object> model = new HashMap<>();
            List<Rutina> rutinas = new ArrayList<Rutina>();
            RutinaDAO rutinaDAO = new RutinaDAO();
            ArrayList<Object> objects = rutinaDAO.readByCliente(email);

            for (Object o : objects ) {
                rutinas.add((Rutina)o);
            }

            model.put("rutinas", rutinas);
            model.put("email", email);
            return new ModelAndView(model, "rutinasCliente.ftl"); // located in src/test/resources/spark/template/freemarker
        }, new FreeMarkerEngine());

        post("/rutinasEntrenador", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();

            String email = peticion.get("Email").getAsString();
            Map<String, Object> model = new HashMap<>();
            List<Rutina> rutinas = new ArrayList<Rutina>();
            RutinaDAO rutinaDAO = new RutinaDAO();
            ArrayList<Object> objects = rutinaDAO.readByEntrenador(email);

            for (Object o : objects ) {
                rutinas.add((Rutina)o);
            }

            model.put("rutinas", rutinas);
            model.put("email", email);
            return new ModelAndView(model, "rutinasEntrenador.ftl"); // located in src/test/resources/spark/template/freemarker
        }, new FreeMarkerEngine());

        post("/membresiasFTL", (request, response) -> {
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();
            Map<String, Object> model = new HashMap<>();
            List<Membresia> membresias = new ArrayList<Membresia>();
            String email = peticion.get("Email").getAsString();
            
            MembresiaDAO membresiaDAO = new MembresiaDAO();
            Membresia membresia = (Membresia)membresiaDAO.getByCliente(email);
            ArrayList<Object> objects = membresiaDAO.readAll();

            for (Object o : objects ) {
                membresias.add((Membresia)o);
            }
            //pruebas
            String prueba = "";
            for(Membresia m : membresias) {
                prueba += m.toString();
            }

            model.put("membresias", membresias);
            model.put("jeje", prueba);
            model.put("email", email);
            model.put("selected", membresia.getId());
            return new ModelAndView(model, "membresias.ftl"); // located in src/test/resources/spark/template/freemarker
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

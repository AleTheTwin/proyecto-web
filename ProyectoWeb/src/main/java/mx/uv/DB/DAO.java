package mx.uv.DB;

import java.util.ArrayList;

public interface DAO {
    public abstract ArrayList<Object> readAll();
    public abstract Object readByIdentifier(String identifier);
    public abstract void update(Object objeto, String identifier);
    public abstract void create(Object objeto);
    public abstract void delete(String identifier);
}

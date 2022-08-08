package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.DataObject;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Database<D extends DataObject>  {
    private final Map<String, D> dataMap;

    public Database() { this.dataMap = new HashMap<>(); }

    public void addObject(D object) throws IdentifierException {
        String id = object.getId();
        addData(id, object);
    }

    public D findById(String id) throws IdentifierException {
        try {
            return this.dataMap.get(id);
        } catch (NullPointerException e) {
            throw new IdentifierException("no object with id exists");
        }

    }

    public void addData(String id, D data) throws IdentifierException {
        if (this.dataMap.containsKey(id)) {
            throw new IdentifierException("id already exists");
        }
        this.dataMap.put(id, data);
    }

    protected Collection<D> getValues() {
        return dataMap.values();
    }
}

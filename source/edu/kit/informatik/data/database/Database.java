package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.DataObject;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implicit implementation of a database, uses an internal HashMap.
 * @param <D> abstract datatype, can be specified to store objects which are part of the DataObject family
 * @author uppyo
 * @version 1.0
 */
public class Database<D extends DataObject>  {
    private final Map<String, D> dataMap;

    public Database() { this.dataMap = new HashMap<>(); }

    /**
     * Add an DataObject to the database.
     * @param object abstract object, from the DataObject family, which is to be added
     * @throws IdentifierException if an object with the same id already exists
     */
    public void addObject(D object) throws IdentifierException {
        String id = object.getId();
        addData(id, object);
    }

    /**
     * Find a DataObject in the database.
     * @param id unique id of the object ib the database
     * @return matching object
     * @throws IdentifierException if no matching object could be found
     */
    public D findById(String id) throws IdentifierException {
        if (this.dataMap.get(id) != null) {
            return this.dataMap.get(id);
        } else {
            throw new IdentifierException();
        }

    }

    private void addData(String id, D data) throws IdentifierException {
        if (this.dataMap.containsKey(id)) {
            throw new IdentifierException();
        }
        this.dataMap.put(id, data);
    }

    /**
     * Dumps all stored values of the database
     * @return Collection of all stored DataObject
     */
    protected Collection<D> getValues() {
        return dataMap.values();
    }
}

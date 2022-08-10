package edu.kit.informatik.data.objects;

/**
 * Interface of a DataObject. A DataObject has to be able to return a id to uniquely identify it in the database
 * @author uppyo
 * @version 1.0
 */
public interface DataObject {

    /**
     * To identify objects in the database a unique id is needed
     * @return String unique identifier
     */
    String getId();

}

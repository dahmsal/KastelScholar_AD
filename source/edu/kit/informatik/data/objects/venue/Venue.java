package edu.kit.informatik.data.objects.venue;

import edu.kit.informatik.data.objects.DataObject;

import java.util.List;
import java.util.Set;

public abstract class Venue implements DataObject {


    public abstract String getName();

    /**
     * create and return the identifier of a venue
     * series and journals have distinct identifiers: ((\bseries\b)|(\bjournal\b)) + name
     * @return id-string, can be used to find object in venue db
     */
    public abstract String getId();

    public abstract Set<String> getKeywords();

    public abstract void addKeywords(List<String> keywords);

}

package edu.kit.informatik.data.objects.venue;

import edu.kit.informatik.data.objects.DataObject;

import java.util.Set;

/**
 * Description for scientific venues, places where publication can be published.
 * @author uppyo
 * @version 1.0
 */
public abstract class Venue implements DataObject {

    /**
     * Get the name of a venue, not identical to Id
     * @return the name as String
     */
    public abstract String getName();

    /**
     * Create and return the identifier of a venue.
     * Series and journals have distinct identifiers: ((\series\)|(\journal\)) + name
     * @return id-string, can be used to find object in venue db
     */
    @Override
    public abstract String getId();

    /**
     * Venues can have their own set of keywords that get passed on to all publications in the venue
     * @return A String-set of all keywords of the venue
     */
    public abstract Set<String> getKeywords();

    /**
     * Add a set of keywords to the venue, duplicates get ignored
     * @param keywords String-set of keywords
     */
    public abstract void addKeywords(Set<String> keywords);



}

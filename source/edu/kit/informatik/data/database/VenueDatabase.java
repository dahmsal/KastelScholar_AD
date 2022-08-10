package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.venue.Series;
import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.messages.DatabaseExceptionMessage;

/**
 * Explicit venue database. Stores Journal and Series Objects. Contains useful queries.
 * @author uppyo
 * @version 1.0
 */
public class VenueDatabase {
    private final Database<Venue> database;

    public VenueDatabase() { this.database = new Database<>(); }

    /**
     * Add a venue to the database.
     * @param venue a new venue DataObject
     * @throws IdentifierException if a venue with same id already exists
     */
    public void addVenue(Venue venue) throws IdentifierException {
        try {
            this.database.addObject(venue);
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptionMessage.getVenueExists(venue.getName()));
        }
    }

    /**
     * Get a venue from the database.
     * @param id identifier of the venue. Is of form: (venue_type) + (venue_name)
     * @return a matching Venue DataObject
     * @throws IdentifierException if no matching object could be found
     */
    public Venue getVenue(String id) throws IdentifierException {
        try {
            return this.database.findById(id);
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptionMessage.getVenueMissing(id));
        }
    }

    /**
     * Get a series by name from the database.
     * @param name name of the series, is not identical with internal id
     * @return a matching Series DataObject
     * @throws IdentifierException if no matching object could be found
     */
    public Series getSeries(String name) throws IdentifierException {
        try {
            return (Series) this.database.findById(Series.createId(name));
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptionMessage.getSeriesMissing(name));
        }
    }

}

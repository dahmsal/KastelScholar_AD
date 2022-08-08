package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.util.exception.IdentifierException;

public class VenueDatabase {
    private final Database<Venue> database;

    public VenueDatabase() { this.database = new Database<Venue>(); }

    public void addVenue(Venue venue) throws IdentifierException {
        this.database.addObject(venue);
    }

    public Venue getVenue(String id) throws IdentifierException {
        return this.database.findById(id);
    }

}

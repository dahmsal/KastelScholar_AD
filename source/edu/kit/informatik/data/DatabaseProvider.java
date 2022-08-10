package edu.kit.informatik.data;

import edu.kit.informatik.data.database.AuthorDatabase;
import edu.kit.informatik.data.database.PublicationDatabase;
import edu.kit.informatik.data.database.VenueDatabase;

/**
 * A provider-class for the databases. Handles initialisation and gives access to the databases.
 * @author uppyo
 * @version 1.0
 */
public class DatabaseProvider {
    private final PublicationDatabase publicationDatabase;
    private final AuthorDatabase authorDatabase;
    private final VenueDatabase venueDatabase;

    /**
     * Initialisation of all databases
     */
    public DatabaseProvider() {
        this.publicationDatabase = new PublicationDatabase();
        this.authorDatabase = new AuthorDatabase();
        this.venueDatabase = new VenueDatabase();
    }

    /**
     * Returns the publication-database
     * @return Database with object type: Publication
     */
    public PublicationDatabase getPublicationDatabase() {
        return publicationDatabase;
    }

    /**
     * Returns the author-database
     * @return Database with object type: Author
     */
    public AuthorDatabase getAuthorDatabase() {
        return authorDatabase;
    }

    /**
     * Returns the venue-database
     * @return Database with object type: Venue
     */
    public VenueDatabase getVenueDatabase() {
        return venueDatabase;
    }
}
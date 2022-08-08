package edu.kit.informatik.data;

import edu.kit.informatik.data.database.AuthorDatabase;
import edu.kit.informatik.data.database.PublicationDatabase;
import edu.kit.informatik.data.database.VenueDatabase;

public class DatabaseProvider {
    private PublicationDatabase publicationDatabase;
    private AuthorDatabase authorDatabase;
    private VenueDatabase venueDatabase;

    public DatabaseProvider() {
        this.publicationDatabase = new PublicationDatabase();
        this.authorDatabase = new AuthorDatabase();
        this.venueDatabase = new VenueDatabase();
    }

    public PublicationDatabase getPublicationDatabase() {
        return publicationDatabase;
    }

    public AuthorDatabase getAuthorDatabase() {
        return authorDatabase;
    }

    public VenueDatabase getVenueDatabase() {
        return venueDatabase;
    }
}
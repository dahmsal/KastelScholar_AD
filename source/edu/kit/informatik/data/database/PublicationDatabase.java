package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.data.objects.venue.Series;
import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.messages.DatabaseExceptionMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Explicit database for publications. Includes a collection of useful queries.
 * @author uppyo
 * @version 1.0
 */
public class PublicationDatabase {
    private final Database<Publication> database;

    /**
     * Constructor, initialises database
     */
    public PublicationDatabase() {
        this.database = new Database<>();
    }

    /**
     * Add a new publication to the database.
     * @param publication publication to be added
     * @throws IdentifierException if a publication with the same id already exists or
     * if no conference was found when adding to series
     */
    public void addPublication(Publication publication) throws IdentifierException {
        // check if a fitting conference exists
        if (publication.getVenue() instanceof Series) {
            try {
                ((Series) publication.getVenue()).getConference(publication.getYear());
            } catch (IdentifierException e) {
                throw new IdentifierException(DatabaseExceptionMessage.getConferenceMissing(publication.getYear()));
            }
        }
        try {
            this.database.addObject(publication);
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptionMessage.getPublicationExists(publication.getId()));
        }

    }

    /**
     * Get a publication from the database.
     * @param id unique identifier of desired publication
     * @return matching publication
     * @throws IdentifierException if no matching publication could be found
     */
    public Publication getPublication(String id) throws IdentifierException {
        try {
            return this.database.findById(id);
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptionMessage.getPublicationMissing(id));
        }

    }

    /**
     * Dump all publications from the database.
     * @return collection of all stored publications
     */
    public Collection<Publication> getAllPublications() {
        return this.database.getValues();
    }

    /**
     * Find all invalid publications in the database. A publication is invalid if no author was added.
     * @return List of ids for all invalid publications
     */
    public List<String> findInvalid() {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            if (!publication.isValid()) {
                returnId.add(publication.getId());
            }
        }
        return returnId;
    }

    /**
     * Find all publications from specified authors.
     * @param authors list of authors
     * @return set of all publications written by all given authors
     */
    public Set<Publication> findByAuthors(List<Author> authors) {
        Set<Publication> returnList = new HashSet<>();
        for (Publication publication: this.database.getValues()) {
            for (Author author: authors) {
                if (publication.getAuthors().contains(author)) {
                    returnList.add(publication);
                    break;
                }
            }
        }
        return returnList;
    }

    /**
     * Find all co-authors of a given author.
     * @param author specified author
     * @return set of co-authors based on publications in this database
     */
    public Set<Author> findCoAuthors(Author author) {
        Set<Author> resultsSet = new HashSet<>();
        for (Publication publication: this.database.getValues()) {
            if (publication.getAuthors().contains(author)) {
                resultsSet.addAll(publication.getAuthors());
            }
        }
        resultsSet.remove(author);
        return resultsSet;
    }

    /**
     * Find all Publications who have certain keywords.
     * @param keywords list of specified keywords
     * @return a list of ids for matching publications
     */
    public List<String> findByKeywords(List<String> keywords) {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            if (publication.getKeywords().containsAll(keywords)) {
                returnId.add(publication.getId());
            }
        }
        return returnId;
    }

    /**
     * Find all Publications from a certain venue.
     * @param venue given venue, can be Journal or Series
     * @return List of all matching publications
     */
    public List<Publication> findByVenue(Venue venue) {
        List<Publication> publicationList = new ArrayList<>();
        for (Publication publication: this.database.getValues()
             ) {
            if (publication.getVenue().equals(venue)) {
                publicationList.add(publication);
            }
        }
        return publicationList;
    }

    /**
     * Finds the number of citations for a given Publication
     * @param givenPub publication, if it is not registered in this database result will be 0
     * @return the count of citations from publications in this database
     */
    public int numberOfCitations(Publication givenPub) {
        int citationCount = 0;
        for (Publication publication:this.database.getValues()) {
            if (publication.getCitations().contains(givenPub)) {
                citationCount++;
            }
        }
        return citationCount;
    }


}

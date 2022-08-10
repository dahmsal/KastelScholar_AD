package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.data.objects.venue.Series;
import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.*;

public class PublicationDatabase {
    private final Database<Publication> database;

    public PublicationDatabase() {
        this.database = new Database<Publication>();
    }

    public void addPublication(Publication publication) throws IdentifierException {
        // check if a fitting conference exists
        if (publication.getVenue() instanceof Series) {
            try {
                ((Series) publication.getVenue()).getConference(publication.getYear());
            } catch (IdentifierException e) {
                throw new IdentifierException("no conference in the given year");
            }
        }
        this.database.addObject(publication);
    }

    public Publication getPublication(String id) throws IdentifierException {
        return this.database.findById(id);
    }

    public Collection<Publication> getAllPublications() {
        return this.database.getValues();
    }

    public List<String> findInvalid() {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            if (!publication.isValid()) {
                returnId.add(publication.getId());
            }
        }
        return returnId;
    }

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

    public List<String> findByKeywords(List<String> keywords) {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            if (publication.getKeywords().containsAll(keywords)) {
                returnId.add(publication.getId());
            }
        }
        return returnId;
    }

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

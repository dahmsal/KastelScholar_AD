package edu.kit.informatik.data.objects;

import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.exception.messages.DataExceptionMessage;
import edu.kit.informatik.util.exception.messages.DatabaseExceptionMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * A scientific Publication, includes useful queries. A publication has to be linked to a venue but does not require an
 * author. A publication is invalid if no author has been added.
 * @author uppyo
 * @version 1.0
 */
public class Publication implements DataObject, Comparable<Publication> {

    private final String id;
    private final int year;
    private final String title;
    private final Venue venue;
    private final List<Author> authors;
    private final Set<String> keywords;
    private final Set<Publication> citations;

    /**
     * Constructor of a publication with minimum data.
     * @param venue a existing venue
     * @param id a unique identifier (lowercase with numbers)
     * @param year int year, when venue is a series a conference has to be in the year
     * @param title full title of the publication
     */
    public Publication(Venue venue, String id, int year, String title) {
        this.venue = venue;
        this.id = id;
        this.year = year;
        this.title = title;
        this.keywords = new HashSet<>();
        this.citations =  new HashSet<>();
        this.authors = new LinkedList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Add a list of authors to the publication
     * @param newAuthors list of author-objects, have to exist in author db
     * @throws ParameterException if any new author was already added
     */
    public void addAuthors(List<Author> newAuthors) throws ParameterException {
        Collections.reverse(newAuthors);
        for (Author author: newAuthors) {
            if (this.authors.contains(author)) {
                throw new ParameterException(DatabaseExceptionMessage.getAuthorWasSet(author.getId()));
            }
        }
        this.authors.addAll(newAuthors);
    }

    /**
     * Add keywords to the publication, duplicates will be ignored
     * @param keywords set of keywords
     */
    public void addKeywords(Set<String> keywords) {
        this.keywords.addAll(keywords);
    }

    /**
     * Get the keywords associated with the publication. Keywords of venue are passed on to the publication.
     * @return Set of all keywords, own and venue combined
     */
    public Set<String> getKeywords() {
        Set<String> returnSet = new HashSet<>();
        returnSet.addAll(this.keywords);
        returnSet.addAll(this.venue.getKeywords());
        if (returnSet.isEmpty()) {
            return Set.of();
        }
        return returnSet;
    }

    /**
     * Get the year of publication
     * @return year as int
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the venue (Series or Journal)
     * @return Venue-Object, associated venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Get the title of the publication
     * @return title as String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Check if a publication is valid. A publication is invalid if no author has been set.
     * @return true if the publication is valid
     */
    public boolean isValid() {
        return !this.authors.isEmpty();
    }

    /**
     * Add a new citation to the publication. A citation has to be a different, older, publication
     * @param publication citation, has to exist in database
     * @throws ParameterException if the citation is already cited, is the same as or newer than the publication
     */
    public void addCitation(Publication publication)  throws ParameterException {
        if (this.citations.contains(publication)) {
            throw new ParameterException(DataExceptionMessage.getCitationExists());
        }
        if (this.equals(publication)) {
            throw new ParameterException(DataExceptionMessage.getCiteSelf());
        }
        if (publication.getYear() > this.year) {
            throw new ParameterException(DataExceptionMessage.getCiteNewer());
        }
        this.citations.add(publication);
    }

    /**
     * Get a List of all authors, in chronological order
     * @return list of authors
     */
    public List<Author> getAuthors() {
        List<Author> returnList = new ArrayList<>(this.authors);
        Collections.reverse(returnList);
        return returnList;
    }

    /**
     * Get the nth author of a publication, n has to be in range of get.Authors.length
     * @param n index of author
     * @return Author at given index
     */
    public Author getNthAuthor(int n) {
        return getAuthors().get(n);
    }

    /**
     * Get all citations of the publication
     * @return Set of publications that ar cited by this publication
     */
    public Set<Publication> getCitations() {
        return citations;
    }

    /**
     * Compare order: Author 1 -> Author N -> Title -> year -> id
     * @param other other publication
     * @return compare result
     */
    @Override
    public int compareTo(Publication other) {
        int n = 0;
        while (n < this.authors.size() & n < other.authors.size()) {
            Author thisAuthor = this.getNthAuthor(n);
            Author otherAuthor = other.getNthAuthor(n);
            int result = thisAuthor.compareTo(otherAuthor);
            // if result is 0  the authors are the same
            if (result != 0) {
                return result;
            }
            n++;
        }
        // next stage: smaller author-list before larger list
        if (this.authors.size() != other.authors.size()) {
            Integer thisSize = this.authors.size();
            Integer otherSize = other.authors.size();
            return thisSize.compareTo(otherSize);
        }
        // next stage: Title
        if (this.title.compareTo(other.title) != 0) {
            return this.title.compareTo(other.title);
        }
        // next stage: year
        if (this.year != other.year) {
            Integer thisYear =  this.year;
            Integer otherYear = other.year;
            return thisYear.compareTo(otherYear);
        }
        // final stage: unique identifier
        return this.getId().compareTo(other.getId());
    }
}

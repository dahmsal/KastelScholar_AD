package edu.kit.informatik.data.objects.venue;

import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.messages.DatabaseExceptions;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.*;

/**
 * A series where publications can be published in context of a conference.
 * To publish a fitting conference has to exist.
 * @author uppyo
 * @version 1.0
 */
public class Series extends Venue {
    private static final String VENUE_TYPE = "series";

    private final String name;
    private final Set<String> keywords;
    private final Map<Integer, Conference> conferences;

    /**
     * Constructor, initialises internal data-structure
     * @param name name of the series
     */
    public Series(String name) {
        this.name =  name;
        this.keywords = new HashSet<>();
        this.conferences = new HashMap<>();
    }

    @Override
    public void addKeywords(Set<String> keywords) {
        this.keywords.addAll(keywords);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return createId(this.name);
    }

    @Override
    public Set<String> getKeywords() {
        return keywords;
    }

    /**
     * Add a conference to series.
     * @param year integer, year of the conference also unique identifier
     * @param location location of the conference
     * @throws IdentifierException if a conference in the same year already exists
     */
    public void addConference(int year, String location) throws IdentifierException {
        if (this.conferences.containsKey(year)) {
            throw new IdentifierException(DatabaseExceptions.getConferenceDouble());
        }
        this.conferences.put(year, new Conference(location));
    }

    /**
     * Get a conference of the series by year.
     * @param year integer, year of the conference
     * @return Conference-Object
     * @throws IdentifierException if no matching object could be found
     */
    public Conference getConference(int year) throws IdentifierException {
        if (this.conferences.containsKey(year)) {
            return this.conferences.get(year);
        }
        throw new IdentifierException("conference doesnt exist");
    }

    /**
     * Create a unique identifier for the venue-database. This makes it possible for Series and Journals with the same
     * Name to co-exist. Format: (Venue_Type) + Whitespace + (Venue_Name)
     * @param name name of the series
     * @return unique internal identifier as string
     */
    public static String createId(String name) {
        return VENUE_TYPE
                + UtilStrings.getWhitespace()
                + name;
    }

    /**
     * Get the location of a conference.
     * @param year identifier of a conference a year
     * @return the location as String
     * @throws IdentifierException if no conference could be found
     */
    public String getLocation(int year) throws IdentifierException {
        return getConference(year).getLocation();
    }

    private static final class Conference {
        private final String location;

        private Conference(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }
    }
}



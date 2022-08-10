package edu.kit.informatik.util.exception.messages;

import edu.kit.informatik.util.strings.UtilStrings;

/**
 * Collection of exception-messages for database operation
 * @author uppyo
 * @version 1.0
 */
public class DatabaseExceptionMessage {

    private static final String AUTHOR = "Author: ";
    private static final String PUBLICATION = "Publication with id: ";
    private static final String VENUE = "Venue with name: ";
    private static final String SERIES = "Series with name: ";

    private static final String MISSING = "could not be found";
    private static final String EXISTS = "already exists";
    private static final String WAS_SET = "was already added";
    private static final String CONFERENCE_MISSING = "no conference could be found in the year: ";
    private static final String CONFERENCE_DOUBLE = "conference with the same year already exists";

    /**
     * Create exception-message
     * @param id name of author
     * @return exception-message as string
     */
    public static String getAuthorMissing(String id) {
        return AUTHOR + UtilStrings.inQuotes(id) + UtilStrings.getWhitespace() + MISSING;
    }

    /**
     * Create exception-message
     * @param id name of author
     * @return exception-message as string
     */
    public static String getAuthorExists(String id) {
        return AUTHOR + UtilStrings.inQuotes(id) + UtilStrings.getWhitespace() + EXISTS;
    }

    /**
     * Create exception-message
     * @param id id of publication
     * @return exception-message as string
     */
    public static String getPublicationMissing(String id) {
        return PUBLICATION + UtilStrings.inQuotes(id) + UtilStrings.getWhitespace() + MISSING;
    }

    /**
     * Create exception-message
     * @param id id of publication
     * @return exception-message as string
     */
    public static String getPublicationExists(String id) {
        return PUBLICATION + UtilStrings.inQuotes(id) + UtilStrings.getWhitespace() + EXISTS;
    }

    /**
     * Create exception-message
     * @param year year of conference
     * @return exception-message as string
     */
    public static String getConferenceMissing(int year) {
        return CONFERENCE_MISSING + year;
    }

    /**
     * Create exception-message
     * @param name name of venue
     * @return exception-message as string
     */
    public static String getVenueMissing(String name) {
        return VENUE + UtilStrings.inQuotes(name) + UtilStrings.getWhitespace() + MISSING;
    }

    /**
     * Create exception-message
     * @param name name of venue
     * @return exception-message as string
     */
    public static String getVenueExists(String name) {
        return VENUE + UtilStrings.inQuotes(name) + UtilStrings.getWhitespace() + EXISTS;
    }

    /**
     * Create exception-message
     * @param name name of venue
     * @return exception-message as string
     */
    public static String getSeriesMissing(String name) {
        return SERIES + UtilStrings.inQuotes(name) + UtilStrings.getWhitespace() + MISSING;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getConferenceDouble() {
        return CONFERENCE_DOUBLE;
    }

    /**
     * Create exception-message
     * @param id name of author
     * @return exception-message as string
     */
    public static String getAuthorWasSet(String id) {
        return AUTHOR + UtilStrings.inQuotes(id) + UtilStrings.getWhitespace() + WAS_SET;
    }
}


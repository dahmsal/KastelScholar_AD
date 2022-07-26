package edu.kit.informatik.util.exception.messages;

/**
 * Collection of exception-messages for data operations
 * @author uppyo
 * @version 1.0
 */
public final class DataExceptionMessage {

    private static final String CITATION_EXISTS = "citation already exists";
    private static final String CITE_SELF = "a publication cannot cite itself";
    private static final String CITE_NEWER = "a citation must be older than the publication";

    private DataExceptionMessage() { }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getCitationExists() {
        return CITATION_EXISTS;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getCiteSelf() {
        return CITE_SELF;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getCiteNewer() {
        return CITE_NEWER;
    }



}

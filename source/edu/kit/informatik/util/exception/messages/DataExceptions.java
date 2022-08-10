package edu.kit.informatik.util.exception.messages;

public class DataExceptions {

    private static final String CITATION_EXISTS = "citation already exists";
    private static final String CITE_SELF = "a publication cannot cite itself";
    private static final String CITE_NEWER = "a citation must be older than the publication";

    public static String getCitationExists() {
        return CITATION_EXISTS;
    }

    public static String getCiteSelf() {
        return CITE_SELF;
    }

    public static String getCiteNewer() {
        return CITE_NEWER;
    }

}

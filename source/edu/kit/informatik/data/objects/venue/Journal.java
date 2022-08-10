package edu.kit.informatik.data.objects.venue;

import edu.kit.informatik.util.strings.UtilStrings;

import java.util.HashSet;
import java.util.Set;

/**
 * Model of a scientific journal, a venue to publish in.
 * @author uppyo
 * @version 1.0
 */
public class Journal extends Venue {
    private static final  String VENUE_TYPE = "journal";

    private final String name;
    private final Set<String> keywords;

    /**
     * A journal can be constructed with a name
     * @param name name of the journal, includes whitespaces
     */
    public Journal(String name) {
        this.name = name;
        this.keywords = new HashSet<>();
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


    private static String createId(String name) {
        return VENUE_TYPE
                + UtilStrings.getWhitespace()
                + name;
    }
}




package edu.kit.informatik.data.objects.venue;

import edu.kit.informatik.util.strings.UtilStrings;

import java.util.HashSet;
import java.util.Set;

public class Journal extends Venue {
    private static final  String VENUE_TYPE = "journal";

    private final String name;
    private final Set<String> keywords;
    private final String publisher;

    public Journal(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
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

    public String getPublisher() {
        return publisher;
    }

    public static String createId(String name) {
        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append(VENUE_TYPE);
        idBuilder.append(UtilStrings.getWhitespace());
        idBuilder.append(name);
        return idBuilder.toString();
    }
}




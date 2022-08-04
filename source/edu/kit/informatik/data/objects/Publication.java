package edu.kit.informatik.data.objects;

import edu.kit.informatik.data.objects.venue.Venue;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Publication {

    private final String id;
    private final int year;
    private final String title;
    private final Venue venue;
    private Author author;
    private Set<String> keywords;
    private Set<Publication> citations;

    public Publication(Venue venue, String id, int year, String title) {
        this.venue = venue;
        this.id = id;
        this.year = year;
        this.title = title;
        this.keywords = new HashSet<>();
        this.citations =  new HashSet<>();
    }

    public void setAuthor(Author author) throws InvalidParameterException {
        if (this.author == null) {
            this.author = author;
        } else {
            throw new InvalidParameterException("author has already been set");
        }
    }

    public Set<String> getKeywords() {
        Set<String> returnSet = new HashSet<>();
        returnSet.addAll(this.keywords);
        returnSet.addAll(this.venue.getKeywords());
        return returnSet;
    }
}

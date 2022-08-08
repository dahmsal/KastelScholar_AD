package edu.kit.informatik.data.objects;

import edu.kit.informatik.data.objects.venue.Venue;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Publication implements DataObject {

    private final String id;
    private final int year;
    private final String title;
    private final Venue venue;
    private Set<Author> authors;
    private Set<String> keywords;
    private Set<Publication> citations;

    public Publication(Venue venue, String id, int year, String title) {
        this.venue = venue;
        this.id = id;
        this.year = year;
        this.title = title;
        this.keywords = new HashSet<>();
        this.citations =  new HashSet<>();
        this.authors = new HashSet<>();
    }

    @Override
    public String getId() {
        return id;
    }

    public void addAuthors(Set<Author> newAuthors) throws InvalidParameterException {
        for (Author author: newAuthors) {
            if (this.authors.contains(author)) {
                throw new InvalidParameterException("author has already been set");
            }
        }
        this.authors.addAll(newAuthors);
    }

    public void addKeywords(Set<String> keywords) {
        this.keywords.addAll(keywords);
    }

    public Set<String> getKeywords() {
        Set<String> returnSet = new HashSet<>();
        returnSet.addAll(this.keywords);
        returnSet.addAll(this.venue.getKeywords());
        if (returnSet.isEmpty()) {
            return Set.of();
        }
        return returnSet;
    }

    public int getYear() {
        return year;
    }

    public boolean isValid() {
        return !this.authors.isEmpty();
    }

    public void addCitation(Publication publication)  throws InvalidParameterException {
        if (this.citations.contains(publication)) {
            throw new InvalidParameterException("citation already exists");
        }
        if (this.equals(publication)) {
            throw new InvalidParameterException("a publication cannot cite itself");
        }
        if (publication.getYear() > this.year) {
            throw new InvalidParameterException("a citation must be older than the publication");
        }
        this.citations.add(publication);

    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Publication> getCitations() {
        return citations;
    }

}

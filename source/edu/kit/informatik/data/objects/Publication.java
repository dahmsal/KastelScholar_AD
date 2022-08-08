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
    }

    @Override
    public String getId() {
        return id;
    }

    public void addAuthor(Author author) throws InvalidParameterException {
        if (this.authors.contains(author)) {
            throw new InvalidParameterException("author has already been set");
        } else {
            this.authors.add(author);
        }
    }

    public void addKeywords(List<String> keywords) {
        this.keywords.addAll(keywords);
    }

    public Set<String> getKeywords() {
        Set<String> returnSet = new HashSet<>();
        returnSet.addAll(this.keywords);
        returnSet.addAll(this.venue.getKeywords());
        return returnSet;
    }

    public boolean isValid() {
        return !this.authors.isEmpty();
    }

    public void addCitation(Publication publication)  throws InvalidParameterException {
        if (!this.citations.contains(publication)) {
            citations.add(publication);
            return;
        }
        if (this.equals(publication)) {
            throw new InvalidParameterException("a publication cannot cite itself");
        }
        throw new InvalidParameterException("citation already exists");
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Publication> getCitations() {
        return citations;
    }

}

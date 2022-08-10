package edu.kit.informatik.data.objects;

import edu.kit.informatik.data.objects.venue.Venue;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.*;

public class Publication implements DataObject, Comparable<Publication>{

    private final String id;
    private final int year;
    private final String title;
    private final Venue venue;
    private List<Author> authors;
    private Set<String> keywords;
    private Set<Publication> citations;

    public Publication(Venue venue, String id, int year, String title) {
        this.venue = venue;
        this.id = id;
        this.year = year;
        this.title = title;
        this.keywords = new HashSet<>();
        this.citations =  new HashSet<>();
        this.authors = new ArrayList<>();
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

    public Venue getVenue() {
        return venue;
    }

    public String getTitle() {
        return title;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public Author getNthAuthor(int n) {
        return authors.get(n);
    }

    public Set<Publication> getCitations() {
        return citations;
    }

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

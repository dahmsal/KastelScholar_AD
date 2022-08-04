package edu.kit.informatik.data.objects.venue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Journal extends Venue {

    private String name;
    private Set<String> keywords;
    private String publisher;

    public Journal(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
        this.keywords = new HashSet<>();
    }
    @Override
    public void addKeywords(List<String> keywords) {
        this.keywords.addAll(keywords);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getKeywords() {
        return keywords;
    }

    public String getPublisher() {
        return publisher;
    }


}

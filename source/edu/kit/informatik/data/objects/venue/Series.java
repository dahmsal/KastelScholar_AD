package edu.kit.informatik.data.objects.venue;

import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.*;

public class Series extends Venue {
    private static final String VENUE_TYPE = "series";

    private final String name;
    private final Set<String> keywords;
    private final Map<Integer, Conference> conferences;


    public Series(String name) {
        this.name =  name;
        this.keywords = new HashSet<>();
        this.conferences = new HashMap<>();
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
    public String getId() {
        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append(VENUE_TYPE);
        idBuilder.append(UtilStrings.getWHITESPACE());
        idBuilder.append(this.name);
        return idBuilder.toString();
    }

    @Override
    public Set<String> getKeywords() {
        return keywords;
    }

    public void addConference(int year, String location) throws IdentifierException {
        if (this.conferences.containsKey(year)) {
            throw new IdentifierException("conference with same year already exists");
        }
        this.conferences.put(year, new Conference(year, location));
    }

    public Conference getConference(int year) throws IdentifierException {
        if (this.conferences.containsKey(year)) {
            return this.conferences.get(year);
        }
        throw new IdentifierException("conference doesnt exist");
    }

    private static final class Conference {
        private final int year;
        private final String location;

        private Conference(int year, String location) {
            this.year = year;
            this.location = location;
        }

        public int getYear() {
            return year;
        }

        public String getLocation() {
            return location;
        }
    }

}

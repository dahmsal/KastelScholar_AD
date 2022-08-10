package edu.kit.informatik.data.objects;

import edu.kit.informatik.util.strings.UtilStrings;

import java.util.Comparator;
import java.util.Locale;

public class Author implements DataObject, Comparable<Author> {
    private String name;
    private String[] nameSplit;

    public Author(String name) {
        this.name = name;
        this.nameSplit = name.split(UtilStrings.getWhitespace());
    }

    @Override
    public String getId() {
        return this.name;
    }

    public String getFirstName() {
        return nameSplit[0];
    }

    public String getLastName() {
        return nameSplit[1];
    }

    public char getFirstLetter() { return getFirstName().toUpperCase(Locale.ROOT).charAt(0); }
    @Override
    public int compareTo(Author o) {
        return Comparator.comparing(Author::getLastName).thenComparing(Author::getFirstName).compare(this, o);
    }
}

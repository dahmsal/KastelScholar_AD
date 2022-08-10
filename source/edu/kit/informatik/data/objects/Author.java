package edu.kit.informatik.data.objects;

import edu.kit.informatik.util.strings.UtilStrings;

import java.util.Comparator;
import java.util.Locale;

/**
 * Description of an author as DataObject. Identifier is the full name of the author.
 * @author uppyo
 * @version 1.0
 */
public class Author implements DataObject, Comparable<Author> {
    private final String name;
    private final String[] nameSplit;

    /**
     * Constructor, creates Author-Object with name.
     * @param name full name (Firstname + Whitespace + Lastname)
     */
    public Author(String name) {
        this.name = name;
        this.nameSplit = name.split(UtilStrings.getWhitespace());
    }

    @Override
    public String getId() {
        return this.name;
    }

    /**
     * Get the first-name or surname of the author.
     * @return String first-name
     */
    public String getFirstName() {
        return nameSplit[0];
    }

    /**
     * Get the last-name or name of the author.
     * @return String last-name
     */
    public String getLastName() {
        return nameSplit[1];
    }

    /**
     * Get the first initial of the author as uppercase
     * @return char first initial
     */
    public char getFirstLetter() { return getFirstName().toUpperCase(Locale.ROOT).charAt(0); }

    /**
     * Compare authors lexicographically by last- and then by firs-name
     * @param o other author
     * @return comparison result
     */
    @Override
    public int compareTo(Author o) {
        return Comparator.comparing(Author::getLastName).thenComparing(Author::getFirstName).compare(this, o);
    }
}

package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.messages.DatabaseExceptions;

/**
 * Explicit database for Objects of type Author.
 * @author uppyo
 * @version 1.0
 */
public class AuthorDatabase {
    private final Database<Author> database;

    /**
     * Constructor, initialises database
     */
    public AuthorDatabase() { this.database = new Database<>(); }

    /**
     * Add a new author-object to the database.
     * @param author author-object to be added
     * @throws IdentifierException if a author with same name already exists
     */
    public void addAuthor(Author author) throws IdentifierException {
        try {
            this.database.addObject(author);
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptions.getAuthorExists(author.getId()));
        }
    }

    /**
     * Get an author object from the database.
     * @param id unique identifier of an author (Full Name)
     * @return corresponding author object
     * @throws IdentifierException if no matching author could be found
     */
    public Author getAuthor(String id) throws IdentifierException {
        try {
            return this.database.findById(id);
        } catch (IdentifierException e) {
            throw new IdentifierException(DatabaseExceptions.getAuthorMissing(id));
        }
    }
}

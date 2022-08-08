package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.util.exception.IdentifierException;

public class AuthorDatabase {
    private final Database<Author> database;

    public AuthorDatabase() { this.database = new Database<Author>(); }

    public void addAuthor(Author author) throws IdentifierException {
        this.database.addObject(author);
    }

    public Author getAuthor(String id) throws IdentifierException {
        return this.database.findById(id);
    }
}

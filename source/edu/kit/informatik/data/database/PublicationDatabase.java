package edu.kit.informatik.data.database;

import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.ArrayList;
import java.util.List;

public class PublicationDatabase {
    private final Database<Publication> database;

    public PublicationDatabase() {
        this.database = new Database<Publication>();
    }

    public void addPublication(Publication publication) throws IdentifierException {
        this.database.addObject(publication);
    }

    public Publication getPublication(String id) throws IdentifierException {
        return this.database.findById(id);
    }

    public List<String> findInvalid() {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            if (!publication.isValid()) {
                returnId.add(publication.getId());
            }
        }
        return returnId;
    }


    public List<String> findByAuthors(List<Author> authors) {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            for(Author author: authors) {
                if (publication.getAuthors().contains(author)) {
                    returnId.add(publication.getId());
                    break;
                }
            }
        }
        return returnId;
    }

    public List<String> findByKeywords(List<String> keywords) {
        ArrayList<String> returnId = new ArrayList<>();
        for (Publication publication: this.database.getValues()) {
            if (publication.getKeywords().containsAll(keywords)) {
                returnId.add(publication.getId());
            }
        }
        return returnId;
    }


}

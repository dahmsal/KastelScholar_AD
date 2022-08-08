package edu.kit.informatik.data.objects;

public class Author implements DataObject {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return this.name;
    }
}

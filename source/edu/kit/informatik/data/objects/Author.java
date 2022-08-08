package edu.kit.informatik.data.objects;

public class Author implements DataObject {
    private String name;
    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = name;
    }

    public String toString() {
        return (this.name + " " + this.surname);
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public String getId() {
        return toString();
    }
}

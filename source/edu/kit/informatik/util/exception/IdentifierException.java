package edu.kit.informatik.util.exception;

/**
 * Custom Exception for issues with unique identifiers in the database
 * @author uppyo
 * @version 1.0
 */
public class IdentifierException extends Exception {
    private static final long serialVersionUID = -5615514978779558261L;

    public IdentifierException(String message) { super(message); }

    public IdentifierException() { super(); }
}

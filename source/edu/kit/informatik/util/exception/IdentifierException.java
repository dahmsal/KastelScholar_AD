package edu.kit.informatik.util.exception;

import edu.kit.informatik.util.strings.UtilStrings;

public class IdentifierException extends Exception {
    private static final long serialVersionUID = -5615514978779558261L;

    public IdentifierException(String message) { super(message); }

    public IdentifierException() { super(); }
}

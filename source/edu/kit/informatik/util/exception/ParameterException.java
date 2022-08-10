package edu.kit.informatik.util.exception;

/**
 * Custom exception that indicates a faulty parameter
 * @author uppyo
 * @version 1.0
 */
public class ParameterException extends Exception {
    private static final long serialVersionUID = -2176940884438141228L;

    public ParameterException(String message) { super(message); }
}

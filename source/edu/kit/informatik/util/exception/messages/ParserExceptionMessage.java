package edu.kit.informatik.util.exception.messages;

/**
 * Collection of exception-messages for parsing operations
 * @author uppyo
 * @version 1.0
 */
public class ParserExceptionMessage {
    private static final String NO_COMMAND = "not a valid command";
    private static final String NUMBER_OF_ARGS = "invalid number of args";
    private static final String NO_ARGS = "no arg provided";
    private static final String WRONG_FORMAT = "wrong parameter format";
    private static final String TYPECAST = "typecast unsuccessful";
    private static final String NO_LIST = "no list provided";

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getNoCommand() {
        return NO_COMMAND;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getNumberOfArgs() {
        return NUMBER_OF_ARGS;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getNoArgs() {
        return NO_ARGS;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getWrongFormat() {
        return WRONG_FORMAT;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getNoList() {
        return NO_LIST;
    }

    /**
     * Create exception-message
     * @return exception-message as string
     */
    public static String getTYPECAST() {
        return TYPECAST;
    }
}

package edu.kit.informatik.util.strings;



public class UtilStrings {
    private static final String WHITESPACE = " ";
    private static final String LINEBREAK = "\n";
    private static final String EMPTYSTRING = "";
    private static final String LBRACKET = "[";
    private static final String RBRACKET = "]";
    private static final String GLQQ = "(";
    private static final String GRQQ = ")";
    private static final String COMMA = ",";
    private static final String DOT = ".";
    private static final String AND = "and";
    private static final String ANDSYMBOL = "&";

    public static String getWhitespace() {
        return WHITESPACE;
    }

    public static String getLinebreak() {
        return LINEBREAK;
    }

    public static String getEmptyString() {
        return EMPTYSTRING;
    }

    public static String getDot() {
        return DOT;
    }

    public static String getComma() {
        return COMMA;
    }

    public static String getAnd() {
        return AND;
    }

    public static String getAndSymbol() {
        return ANDSYMBOL;
    }

    public static String getBracketInt(int i) {
        return LBRACKET + i + RBRACKET;
    }

    public static String getQQInt(int i) {
        return GLQQ + i + GRQQ;
    }


}

package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterWithField;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.exception.messages.ParserExceptionMessage;
import edu.kit.informatik.util.strings.UtilStrings;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Parser for parameters of user-input commands
 * @author uppyo
 * @version 1.0
 */
public final class ParameterParser {

    private ParameterParser() { }

    /**
     * Parse a list of parameters against user-inputs
     * @param parameters list of parameters
     * @param paramInput user input
     * @return Dictionary with key:parameter field:List of Objects
     * @throws InputException if an invalid number of args got parsed
     */
    public static Dictionary<Parameter, List<Object>> parseArguments(List<Parameter> parameters, String paramInput)
            throws InputException {
        Hashtable<Parameter, List<Object>> parameterBundle = new Hashtable<>();
        Scanner parameterScanner = new Scanner(paramInput);
        parameterScanner.useDelimiter(UtilStrings.getComma());
        for (Parameter param : parameters) {
            if (!parameterScanner.hasNext()) {
                throw new InputException(ParserExceptionMessage.getNumberOfArgs());
            }
            if (param.hasSpaceDelimiter()) {
                parameterScanner.useDelimiter(UtilStrings.getWhitespace());
            }
            if (param.isAsList()) {
                parameterBundle.put(param, parseList(parameterScanner.next().trim(), param));
            }
            else if (param.isAsField()) {
                Scanner fieldScanner = new Scanner(paramInput);
                fieldScanner.useDelimiter(UtilStrings.getDdot());
                if (!fieldScanner.hasNext()) {
                    throw new InputException(ParserExceptionMessage.getNoArgs());
                }
                String fieldToken = fieldScanner.next().trim();
                if (fieldScanner.hasNext()) {
                    parameterBundle.put(param, parseParameter(fieldToken, param));
                }
                try {
                    String nextToken = fieldScanner.next();
                    Dictionary<Parameter, List<Object>> parsedField
                            = parseArguments(((ParameterWithField) param).getParameterField(), nextToken);
                    parameterBundle.putAll((Map<Parameter, List<Object>>) parsedField);
                } catch (NoSuchElementException e) {
                    throw new InputException(ParserExceptionMessage.getNoArgs());
                } return parameterBundle;

            } else {
                parameterBundle.put(param, parseParameter(parameterScanner.next().trim(), param));
            }
        }
        if (parameterScanner.hasNext()) {
            throw new InputException(ParserExceptionMessage.getNumberOfArgs());
        }
        return parameterBundle;
    }

    private static List<Object> parseParameter(String inputToken, Parameter parameter) throws InputException {
        Pattern pattern = Pattern.compile(parameter.getPattern());
        if (pattern.matcher(inputToken).matches()) {
            try {
                return List.of(parameter.getType().cast(inputToken));
            } catch (ClassCastException E) {
                throw new InputException(ParserExceptionMessage.getTYPECAST());
            }
        } else if (parameter.getAlternativeParameters() != null) {
            for (Parameter altParam : parameter.getAlternativeParameters()) {
                return parseParameter(inputToken, altParam);
            }
            throw new InputException(ParserExceptionMessage.getWrongFormat());
        } else {
            throw new InputException(ParserExceptionMessage.getWrongFormat());
        }
    }

    private static List<Object> parseList(String inputToken, Parameter parameter) throws  InputException {
        ArrayList<Object> listParameters = new ArrayList<>();
        Scanner listScanner = new Scanner(inputToken);
        listScanner.useDelimiter(UtilStrings.getSemicolon());
        if (listScanner.hasNext()) {
            do {
                String nextInList = listScanner.next().trim();
                listParameters.add(parseParameter(nextInList, parameter).get(0));
            } while (listScanner.hasNext());
            return listParameters;
        } else { throw  new InputException(ParserExceptionMessage.getNoList()); }
    }

}

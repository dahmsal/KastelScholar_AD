package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterWithField;
import edu.kit.informatik.util.exception.InputException;

import java.util.*;
import java.util.regex.Pattern;

public class ParameterParser {

    public static Dictionary<Parameter, List<Object>> parseArguments(List<Parameter> parameters, String paramInput)
            throws InputException {
        Hashtable<Parameter, List<Object>> parameterBundle = new Hashtable<>();
        Scanner parameterScanner = new Scanner(paramInput);
        parameterScanner.useDelimiter(",");
        for (Parameter param : parameters) {
            if (!parameterScanner.hasNext()) {
                throw new InputException("invalid number of args");
            }
            if (param.isAsList()) {
                parameterBundle.put(param, parseList(parameterScanner.next().trim(), param));
            }
            else if (param.isAsField()) {
                Scanner fieldScanner = new Scanner(parameterScanner.next().trim());
                fieldScanner.useDelimiter(":");
                if (!fieldScanner.hasNext()) {
                    throw new InputException("no arg provided");
                }
                String fieldToken = fieldScanner.next().trim();
                if (fieldScanner.hasNext()) {
                    parameterBundle.put(param, parseParameter(fieldToken, param));
                }
                Dictionary<Parameter, List<Object>> parsedField
                        = parseArguments(((ParameterWithField) param).getParameterField(), fieldScanner.next());
                parameterBundle.putAll((Map<Parameter, List<Object>>) parsedField);
            } else {
                parameterBundle.put(param, parseParameter(parameterScanner.next().trim(), param));
            }
        }
        if (parameterScanner.hasNext()) {
            throw new InputException("invalid number of args");
        }
        return parameterBundle;
    }

    private static List<Object> parseParameter(String inputToken, Parameter parameter) throws InputException {
        Pattern pattern = Pattern.compile(parameter.getPattern());
        if (pattern.matcher(inputToken).matches()) {
            try {
                return List.of(parameter.getType().cast(inputToken));
            } catch (ClassCastException E) {
                throw new InputException("typecast unsuccessful");
            }
        } else if (parameter.getAlternativeParameters() != null) {
            for (Parameter altParam : parameter.getAlternativeParameters()) {
                try {
                    return parseParameter(inputToken, altParam);
                } catch (InputException ignored) {
                }
            }
            throw new InputException("wrong parameter format");
        } else {
            throw new InputException("wrong parameter format");
        }
    }

    private static List<Object> parseList(String inputToken, Parameter parameter) throws  InputException {
        ArrayList<Object> listParameters = new ArrayList<Object>();
        Parameter returnParam = null;
        Scanner listScanner = new Scanner(inputToken);
        listScanner.useDelimiter(";");
        if (listScanner.hasNext()) {
            do {
                String nextInList = listScanner.next().trim();
                listParameters.add(parseParameter(nextInList, parameter));
            } while (listScanner.hasNext());
            return listParameters;
        } else { throw  new InputException("no list provided"); }
    }

/*
    private static Dictionary<Parameter, Object> parseField(String inputToken, ParameterWithField parameterWithField)
            throws InputException {
        Hashtable<Parameter, Object> fieldBundle = new Hashtable<>();
        Scanner fieldScanner = new Scanner(inputToken);
        fieldScanner.useDelimiter(":");
        if (!fieldScanner.hasNext()) {
            throw new InputException("no arg provided");
        }
        String fieldToken = fieldScanner.next().trim();
        if (fieldScanner.hasNext()) {
            fieldBundle.put(parameterWithField, parseParameter(fieldToken, parameterWithField));
            Dictionary<Parameter, Object> parsedField
                    = parseArguments(parameterWithField.getParameterField(), fieldScanner.next());
            fieldBundle.putAll((Map<? extends Parameter, ?>) parsedField);
            return fieldBundle;
        } else {
            throw new InputException("no field of args provided");
        }
    }
*/

}

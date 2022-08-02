package edu.kit.informatik.ui.Parser;

import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterWithField;
import edu.kit.informatik.ui.session.InputException;
import edu.kit.informatik.util.ObjectPair;

import java.util.*;
import java.util.regex.Pattern;

public class ParameterParser {

    public static Dictionary<Parameter, Object> parseArguments(List<Parameter> parameters, String paramInput)
            throws InputException {
        Hashtable<Parameter, Object> parameterBundle = new Hashtable<>();
        Scanner parameterScanner = new Scanner(paramInput);
        parameterScanner.useDelimiter(",");
        for (Parameter param : parameters) {
            if (!parameterScanner.hasNext()) {
                throw new InputException("invalid number of args");
            }
            ObjectPair<Parameter, Object> parseResult = new ObjectPair<Parameter, Object>(null, null);
            if (param.isAsList()) {
                parameterBundle.put(param, parseList(parameterScanner.next().trim(), param));
            }
            else if (param.isAsField()) {
                parameterBundle.put(param, parseField(parameterScanner.next().trim(), (ParameterWithField) param));
                // a field must be the last parameter to be accessed (else the recursive parser wont work)
                return parameterBundle;
            } else {
                parameterBundle.put(param, parseParameter(parameterScanner.next().trim(), param));
            }

        }
        return parameterBundle;
    }

    private static Object parseParameter(String inputToken, Parameter parameter) throws InputException {
        Pattern pattern = Pattern.compile(parameter.getPattern());
        if (pattern.matcher(inputToken).matches()) {
            try {
                return new ObjectPair<>(parameter, parameter.getType().cast(inputToken));
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

    private static Dictionary<Object, Object> parseField(String inputToken, ParameterWithField parameterWithField)
            throws InputException {
        Hashtable<Object, Object> fieldBundle = new Hashtable<>();
        Scanner fieldScanner = new Scanner(inputToken);
        fieldScanner.useDelimiter(":");
        if (!fieldScanner.hasNext()) {
            throw new InputException("no arg provided");
        }
        String fieldToken = fieldScanner.next().trim();
        if (fieldScanner.hasNext()) {
            fieldBundle.put(parseParameter(fieldToken, parameterWithField),
                    parseArguments(parameterWithField.getParameterField(), fieldScanner.next()));
            return fieldBundle;
        } else {
            throw new InputException("no field of args provided");
        }
    }

    private static ArrayList<Object> parseList(String inputToken, Parameter parameter) throws  InputException {
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
}
